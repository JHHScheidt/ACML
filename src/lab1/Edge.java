/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author Marciano
 */
public class Edge {
    
    private Vertex input, output;
    private double w;
    
    public Edge(Vertex input, Vertex output, double w){
        this.input = input;
        this.output = output;
        this.w = w;
    }
    
    public void setWeight(double w){
        this.w = w;
    }
    public double getWeight(){
        return this.w;
    }
    public Vertex getVertexInput(){
        return this.input;
    }
    public Vertex getVertexOutput(){
        return this.output;
    }
}