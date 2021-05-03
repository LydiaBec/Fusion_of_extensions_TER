package fusion;

import java.util.Vector;

public class AggregateMax extends Aggregate_Function {
	@Override
	public Vector<Float> choosenAggregate(Models mod) {
		Vector<Float> max = new Vector<>();
		for (Vector<Integer> model : mod.getDistance())
			for (int i = 0; i < model.size(); i++)
				if (max.size() <= i)
					max.add((float)model.get(i));
				else
					max.set(i, Math.max(max.get(i), model.get(i)));
		return max;
	}
}
