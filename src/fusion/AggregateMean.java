package fusion;

import java.util.Vector;
//Mean aggregation function
public class AggregateMean extends Aggregate_Function {

	@Override
	public Vector<Float> choosenAggregate(Models mod) {
		Vector<Float> mean = new Vector<>();
		for (int i = 0; i < mod.getDistance().size(); i++)
			mean.add(0f);

		for (int i = 0; i < mod.getDistance().size(); i++)
			for (int j = 0; j < mod.getDistance().get(i).size(); j++)
				mean.set(j, mean.get(j) + ((float)mod.getDistance().get(i).get(j))/mod.getDistance().get(i).size());
		System.err.println(mod.getDistance());
		return mean;
	}
}
