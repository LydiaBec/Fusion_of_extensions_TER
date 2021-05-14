package fusion;

import java.util.Vector;
// Sum aggregation function 
public class AggregateSum extends Aggregate_Function{
    @Override
    public Vector<Float> choosenAggregate(Models mod) {
        Vector<Float> sum = new Vector<>();
        for(Vector<Integer> model : mod.getDistance())
        	for(int i = 0; i<model.size(); i++)
        		if(sum.size() <= i) sum.add((float)model.get(i));
        		else sum.set(i, sum.get(i)+model.get(i));
        return sum;
    }
}
