package lab1;

import java.util.ArrayList;

public class Vertex {
    private double value = 0.0;
    private ArrayList<Edge> inputEdges;
    private ArrayList<Edge> outputEdges;
    private double delta;
    private boolean biased = false;

    public Vertex(){
        
    }
    
    public Vertex( double value, boolean biased ) {
        this.value=value;
        this.biased = biased;
    }

    public void addInputEdge( Edge e ) {
        this.inputEdges.add(e);
    }

    public void addOutputEdge( Edge e ) {
        this.outputEdges.add(e);
    }

    public double getValue() {
        return this.value;
    }

    public double getDelta() {
        return this.delta;
    }

    public void setValue(double value) {
        this.value=value;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public ArrayList<Edge> getInputEdges() {
        return this.inputEdges;
    }

    public ArrayList<Edge> getOutputEdges() {
        return this.outputEdges;
    }

    public boolean getBias() {
        return this.biased;
    }
}
