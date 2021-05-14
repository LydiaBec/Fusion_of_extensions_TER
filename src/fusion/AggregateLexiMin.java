package fusion;

import java.util.Collections;
import java.util.Vector;
//LexiMin aggregation function
public class AggregateLexiMin extends Aggregate_Function {


	public static Vector<Vector<Integer>> cmpareVec(Vector<Integer> vec1, Vector<Vector<Integer>> vec2) {
		for (int i = 0; i < vec2.size(); i++) {
			for (int j = 0; j < vec2.get(i).size(); j++) {
				if (vec1.get(j) > vec2.get(i).get(j)) {
					vec2.removeElement(vec1);
					break;
				}
			}
		}
		return vec2;
	}
	@Override
	public Vector<Float> choosenAggregate(Models mod) {
		Vector<Float> lex = new Vector<>();
		Vector<Vector<Integer>> lex2 = new Vector<>();
		Vector<Vector<Integer>> vTrans = Models.transpose(mod.getDistance());
		for (int i = 0; i < vTrans.size(); i++) {
			lex.add((float) 1.0);
		}
		for (int j = 0; j < vTrans.size(); j++) {
			Collections.sort(vTrans.get(j));
		}
		lex2.addAll(vTrans);
		for (int i = 0; i < vTrans.size(); i++) {
			lex2 = cmpareVec(vTrans.get(i), lex2);
		}
		for (int i = 0; i < vTrans.size(); i++) {
			for (int j = 0; j < lex2.size(); j++) {
				if (vTrans.get(i) == lex2.get(j)) {
					lex.set(i, (float) 0.0);
				}
			}
		}
		return lex;
	}
}
