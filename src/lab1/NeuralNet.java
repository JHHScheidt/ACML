/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Marciano
 */
public class NeuralNet {

    private ArrayList<ArrayList<Vertex>> vertices;
    private ArrayList<ArrayList<Edge>> edges;
    private static Random generator = new Random(3);
    private final double biasConstant = 1;
    private LearningMethod learner;
    private ArrayList<ArrayList<double[]>> data;

    public NeuralNet(int[] layers, boolean[] bias, LearningMethod learner){
        this.vertices = new ArrayList<ArrayList<Vertex>>();
        this.edges = new ArrayList<ArrayList<Edge>>();
        this.learner = learner;

        for(int i = 0; i < layers.length; i++){
            vertices.add(new ArrayList<Vertex>());
            if(i != layers.length - 1){
                if(bias[i]){
                    vertices.get(i).add(new Vertex(biasConstant, true));
                }
            }
            for(int j = 0; j < layers[i]; j++){
                vertices.get(i).add(new Vertex());
            }
        }
        for(int i = 0; i < vertices.size() - 1; i++){
            edges.add(new ArrayList<Edge>());
            for(int j = 0; j < vertices.get(i).size(); j++){
                for(int k = 0; k < vertices.get(i+1).size(); k++){
                    if(k == 0 && bias[i+1]){

                    } else {
                        Edge temp = new Edge(vertices.get(i).get(j), vertices.get(i+1).get(k), generator.nextDouble()*2);
                        edges.get(i).add(temp);
                        vertices.get(i).get(j).addOutputEdge(temp);
                        vertices.get(i+1).get(k).addInputEdge(temp);
                    }
                }
            }
        }
    }

    public void setAllWeights(double[][] weights){
        if(weights.length != edges.size()){
            System.out.println("NUMBER OF LAYER FROM WEIGTHS INCORRECT WITH LAYER OF EDGES");
            return;
        }
        for(int i = 0; i < edges.size(); i++){
            if(weights[i].length != edges.get(i).size()){
                System.out.println("WEIGHTS TO EDGES MISMATCH AT LAYER: " + i);
                return;
            }
        }
        for(int i = 0; i < edges.size(); i++){
            for(int j = 0; j < edges.get(i).size(); j++){
                edges.get(i).get(j).setWeight(weights[i][j]);
            }
        }
    }
    
    public void printOutputValue(){
        System.out.println("The current values on the output nodes: ");
        for (Vertex v : vertices.get(vertices.size()-1)) {
            System.out.print( v.getValue() +"; ");
        }
    }
    public void printWeights(){
        for(int i = 0; i < edges.size(); i++){
            System.out.print("The weights for edge layer: " + i + " are ");
            for(int j = 0; j < edges.get(i).size(); j++){
                System.out.print(edges.get(i).get(j).getWeight() + "; ");
            }
            System.out.println("");
        }
    }
    public void printNet(){
        System.out.println("Number of layers: " + vertices.size());
        System.out.println("Number of connection layers: " + edges.size());
        int numVertices = 0;
        for(int i = 0; i < vertices.size(); i++){
            numVertices += vertices.get(i).size();
        }
        int numEdges = 0;
        for(int i = 0; i < edges.size(); i++){
            numEdges += edges.get(i).size();
        }
        System.out.println("Number of vertices: " + numVertices);
        System.out.println("Number of edges: " + numEdges);
    }

    public void setData(ArrayList<ArrayList<double[]>> data) {
        this.data = data;
    }

    public void learn() {
        learner.setData(this.data, this.edges, this.vertices);
        learner.learnWeights();
    }
}
