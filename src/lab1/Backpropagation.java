package lab1;

import java.util.ArrayList;

public class Backpropagation implements LearningMethod {

    ArrayList<ArrayList<double[]>> data;
    ArrayList<ArrayList<Edge>> edges;
    ArrayList<ArrayList<Vertex>> vertices;

    public Backpropagation() {

    }

    @Override
    public void setData(ArrayList<ArrayList<double[]>> data, ArrayList<ArrayList<Edge>> edges, ArrayList<ArrayList<Vertex>> vertices) {
        this.data = data;
        this.edges = edges;
        this.vertices = vertices;
    }

    @Override
    public void learnWeights() {

    }

}
