package fusion;

import java.util.Collection;

public class DistanceHamming extends Distance {

    // function to calculate Hamming distance
    public int choosenDistance(Collection<String> Ext, Collection<String> Mod) {
        int difference = 0;
        for (String i : Ext) {
            if (!Mod.contains(i)) {
                difference++;
            }
        }
        for (String i : Mod) {
            if (!Ext.contains(i)) {
                difference++;
            }
        }

        return difference;
    }

 

}
