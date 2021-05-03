package fusion;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class Models {
	private Collection<Collection<String>> models = null;
	private Vector<Vector<Integer>> distance = new Vector<>();

	public Models(Collection<Collection<String>> model) {
		this.models = model;
	}

	public Collection<Collection<String>> getModels() {
		return this.models;
	}

	public void setDistance(Vector<Integer> dist) {
		this.distance.add(dist);
	}

	public Vector<Vector<Integer>> getDistance() {
		return this.distance;
	}

	public Collection<Collection<String>> getCondidats(Vector<Float> resultats) {
		Collection<Collection<String>> candidat = new Vector<>();
		int index = 0;
		float dist, min = Collections.min(resultats);
		for (Collection<String> current_mod : this.getModels()) {
			dist = resultats.get(index);
			if (dist == min) {
				candidat.add(current_mod);
			}
			index++;
		}
		return candidat;
	}
	static Vector<Vector<Integer>> transpose(Vector<Vector<Integer>> vect) {
		Vector<Vector<Integer>> transposed = new Vector<>();
		for (int i = 0; i < vect.get(0).size(); i++)
			transposed.add(new Vector<Integer>());

		for (int i = 0; i < vect.size(); i++)
			for (int j = 0; j < vect.get(i).size(); j++)
				transposed.get(j).add(vect.get(i).get(j));
		return transposed;
	}
}
