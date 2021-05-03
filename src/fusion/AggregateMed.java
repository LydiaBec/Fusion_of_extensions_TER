package fusion;

import java.util.Collections;
import java.util.Vector;

public class AggregateMed extends Aggregate_Function {



	// Function for calculating median
	public static double findMedian(Vector<Integer> vector) {
		Collections.sort(vector);
		int m = vector.size();
		if (m % 2 != 0)
			return vector.get(m / 2);
		return vector.get((m - 1) / 2) + vector.get(m / 2) / 2.0;
	}

	@Override
	public Vector<Float> choosenAggregate(Models mod) {
		Vector<Float> vecMedaine = new Vector<>();
		for (Vector<Integer> model : Models.transpose(mod.getDistance())) {
			float med = (float) findMedian(model);
			vecMedaine.add(med);
		}
		return vecMedaine;
	}
}