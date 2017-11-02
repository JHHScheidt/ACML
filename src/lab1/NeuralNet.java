/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Marciano
 */
public class NeuralNet {
    
    private ArrayList<ArrayList<Vertex>> nodes;
    private ArrayList<ArrayList<Edge>> edges;
    private static Random generator = new Random(3);
    
    public NeuralNet(int[] layers, boolean[] bias){
        this.nodes = new ArrayList<ArrayList<Vertex>>();
        this.edges = new ArrayList<ArrayList<Edge>>();
        
        for(int i = 0; i < layers.length; i++){
            if(i != layers.length){
                if(bias[i]){
                    nodes.get(i).add(new Vertex(1));
                }
                for(int j = 0; j < layers[i]; j++){
                    nodes.get(i).add(new Vertex());
                }
            }
            else{
                for(int j = 0; j < layers[i]; j++){
                    nodes.get(i).add(new Vertex());
                }
            }
        }
        for(int i = 0; i < nodes.size() - 1; i++){
            for(int j = 0; j < nodes.get(i).size(); j++){
                for(int k = 0; k < nodes.get(i+1).size(); k++){
                    edges.get(i).add(new Edge(nodes.get(i).get(j), nodes.get(i+1).get(k), generator.nextDouble()*2));
                }
            }
        }
    }
}
