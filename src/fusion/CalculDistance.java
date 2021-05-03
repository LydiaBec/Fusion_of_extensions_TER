package fusion;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;
import net.sf.jargsemsat.jargsemsat.datastructures.DungAF;

public class CalculDistance {

	public static void calculDistance(DungAF af, Models mod, Distance distance, String semantic) {
		int dist = 0;
		Object min = null;
		Vector<Integer> vec_distance = new Vector<>();
		// Iterator<HashSet<String>> iterator_ext;
		Vector<Integer> Min_distance = new Vector<Integer>();
		// parourir les modèles
		for (Collection<String> current_mod : mod.getModels()) {
			
			HashSet<HashSet<String>> ext = null;
			switch (semantic) {
			case "CO":
				ext = af.getCompleteExts();
				break;
			case "ST":
				ext = af.getStableExts();
				break;
			case "GR":
				ext = new HashSet<HashSet<String>>();
				ext.add(af.getGroundedExt());
				break;
			case "PR":
				ext = af.getPreferredExts();
				break;
			}
			//System.out.println("MODELE " + i_mod + " : " + current_mod);
		//	System.out.println("EXTENSION " + ext);
			for (Collection<String> current_ext : ext) {
			//	System.out.println("Distance Avec Extension " + current_ext);
				dist = distance.choosenDistance(current_ext, current_mod);
			//	System.out.println(" = " + dist);
				// vecteur pour trouver le minimum des distance d'un modèle
				Min_distance.addElement(dist);
				min = Collections.min(Min_distance);
			}
			vec_distance.addElement((Integer) min);
		//	System.out.println("Distance minimum du modèle  : " + min);
			Min_distance.clear();
		}
		mod.setDistance(vec_distance);

	}

}
