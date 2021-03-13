package ter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import net.sf.jargsemsat.jargsemsat.datastructures.DungAF;

public class CalculDistance {
	public static String format_s(String str) {
		String result;
		result = str.replace("[", "");
		result = result.replace(",", "");
		result = result.replace(" ", "");
		result = result.replace("]", "");
		return result;
	}

	public static void calculDistance(DungAF af, Models mod, DistanceHamming dm) {
		int i_mod = 0, j_ext, dist;
		Object min = null;
		Vector<Integer> vec_distance = new Vector<>();
		Iterator<HashSet<String>> iterator_ext;
		Iterator<String> iterator_mod = mod.getModels().iterator();
		Vector<Integer> Min_distance = new Vector<Integer>();

		while (iterator_mod.hasNext()) {
			String current_mod = iterator_mod.next().toString();
			i_mod++;
			System.out.println("modele " + i_mod + " : " + current_mod);
			iterator_ext = af.getStableExts().iterator();
			j_ext = 0;
			while (iterator_ext.hasNext()) {
				String current_ext = iterator_ext.next().toString();
				j_ext++;
				System.out.println("Extension " + j_ext + " : " + current_ext);
				/* Hamming distance */
				dist = DistanceHamming.hammingDist(format_s(current_ext), format_s(current_mod));
				System.out.println(
						"Distance : " + DistanceHamming.hammingDist(format_s(current_ext), format_s(current_mod)));
				// vecteur pour trouver le minimum des distance d'un mod�le
				Min_distance.addElement(dist);
				min = Collections.min(Min_distance);

			}

			vec_distance.addElement((Integer) min);
			System.out.println("Distance modele : " + min);
			Min_distance.clear();
		}
		mod.setDistance(vec_distance);

	}
}
