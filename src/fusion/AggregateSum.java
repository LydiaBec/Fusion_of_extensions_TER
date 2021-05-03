/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fusion;

import java.util.Vector;
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
