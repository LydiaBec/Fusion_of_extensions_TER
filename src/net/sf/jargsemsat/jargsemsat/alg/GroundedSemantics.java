package net.sf.jargsemsat.jargsemsat.alg;

import java.util.Iterator;
import java.util.Vector;

import net.sf.jargsemsat.jargsemsat.datastructures.DungAF;
import net.sf.jargsemsat.jargsemsat.datastructures.Encoding;
import net.sf.jargsemsat.jargsemsat.datastructures.Labelling;
import net.sf.jargsemsat.jargsemsat.datastructures.OrClause;
import net.sf.jargsemsat.jargsemsat.datastructures.SATFormulae;

public class GroundedSemantics extends CompleteSemantics {

	public static boolean extensions(Vector<Labelling> ret, DungAF af,
			Encoding enc, String arg, boolean firstonly) {
		Misc.disclaimer();

		SATFormulae cnf = basicComplete(af, enc);

		Labelling grcand = new Labelling();

		while (true) {
			Labelling res = new Labelling();
			if (!satlab(cnf, res, af)) {
				break;
			}

			grcand = new Labelling();

			grcand = res.clone();

			if (res.undecargs().size() == af.getArguments().size())
				break;

			for (Iterator<String> iter = res.undecargs().iterator(); iter
					.hasNext();) {
				cnf.appendOrClause(
						new OrClause(new int[]{af.UndecVar(iter.next())}));
			}

			OrClause remaining = new OrClause();
			for (Iterator<String> iter = res.outargs().iterator(); iter
					.hasNext();) {
				remaining.appendVariable(af.UndecVar(iter.next()));
			}
			for (Iterator<String> iter = res.inargs().iterator(); iter
					.hasNext();) {
				remaining.appendVariable(af.UndecVar(iter.next()));
			}
			cnf.appendOrClause(remaining);
		}

		if (arg == null) {
			ret.add(grcand);
		} else {
			return grcand.inargs().contains(arg);
		}
		return true;
	}

	public static boolean credulousAcceptance(String arg, DungAF af,
			Encoding enc) {
		Misc.disclaimer();
		return extensions(new Vector<Labelling>(), af, enc, arg, false);
	}

	public static boolean skepticalAcceptance(String arg, DungAF af,
			Encoding enc) {
		Misc.disclaimer();
		return extensions(new Vector<Labelling>(), af, enc, arg, false);
	}

	public static boolean someExtension(Labelling ret, DungAF af,
			Encoding enc) {
		Misc.disclaimer();
		Vector<Labelling> res = new Vector<Labelling>();
		boolean val = extensions(res, af, enc, null, false);
		ret.copyFrom(res.firstElement());
		return val;
	}

}
