/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.ArrayList;

/**
 *
 * @author Marciano
 */
public class NeuralNet {
    
    private ArrayList<ArrayList<Vertex>> nodes;
    private ArrayList<ArrayList<Edge>> edges;
    
    public NeuralNet(int[] layers, boolean[] bias){
        this.nodes = new ArrayList<ArrayList<Vertex>>();
        this.edges = new ArrayList<ArrayList<Edge>>();
        for(int i = 0; i < layers.length; i++){
            if(i != layers.length){
                if(bias[i]){
                    
                }
            }
        }
    }
}
