/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.util.Collection;
import java.util.Vector;

/**
 *
 * @author pc
 */
public class Models {
    private Vector<String> models = null;
    private Vector<Integer> distance = new Vector<>();
    
    public Models(Vector<String> model){
        this.models = model;
    }
    
    public Collection<String> getModels(){
            return this.models;
	}
    
    public void setDistance(Vector<Integer> dist){
        this.distance = dist;
    }
    
    public Collection<Integer> getDistance(){
        return this.distance;
    }
    
}
