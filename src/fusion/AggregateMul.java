package fusion;

import java.util.Vector;
//aggregation function multiplication
public class AggregateMul extends Aggregate_Function{
    @Override
    public Vector<Float> choosenAggregate(Models mod) {
        Vector<Float> mul = new Vector<>();
        for(Vector<Integer> model : mod.getDistance()) {
        	for(int i = 0; i<model.size(); i++)
        		if(mul.size() <= i) mul.add((float)model.get(i));
        		else mul.set(i, mul.get(i)*model.get(i));
        }
        return mul;
    }
}
