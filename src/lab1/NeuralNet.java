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
    private final double biasConstant = 1;
    
    public NeuralNet(int[] layers, boolean[] bias){
        this.nodes = new ArrayList<ArrayList<Vertex>>();
        this.edges = new ArrayList<ArrayList<Edge>>();
        
        for(int i = 0; i < layers.length; i++){
            if(i != layers.length - 1){
                if(bias[i]){
                    nodes.get(i).add(new Vertex(biasConstant));
                }
                
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
    
    public void setAllWeights(double[][] weights){
        if(weights.length != edges.size()){
            System.out.println("NUMBER OF LAYER FROM WEIGTHS INCORRECT WITH LAYER OF EDGES");
        }
        for(int i = 0; i < edges.size(); i++){
            if(weights[i].length != edges.get(i).size()){
                System.out.println("WEIGHTS TO EDGES MISMATCH AT LAYER: " + i);
            }
        }
        for(int i = 0; i < edges.size(); i++){
            for(int j = 0; j < edges.get(i).size(); j++){
                edges.get(i).get(j).setWeight(weights[i][j]);
            }
        }
    }
    
    public void printWeights(){
        for(int i = 0; i < edges.size(); i++){
            System.out.println("The weights for the layer: " + i + " are ");
            for(int j = 0; j < edges.get(i).size(); j++){
                System.out.print(edges.get(i).get(j).getWeight());
            }
        }
    }
    public void printNet(){
        System.out.println("Number of layers: " + nodes.size());
        System.out.println("Number of connection layers: " + edges.size());
        int numNodes = 0;
        for(int i = 0; i < nodes.size(); i++){
            numNodes += nodes.get(i).size();
        }
        int numEdges = 0;
        for(int i = 0; i < edges.size(); i++){
            numEdges += edges.get(i).size();
        }
        System.out.println("Number of nodes: " + numNodes);
        System.out.println("Number of edges: " + numEdges);
    }
}
