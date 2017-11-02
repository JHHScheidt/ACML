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

    public ArrayList<ArrayList<Double>> learningIteration() {
        ArrayList<ArrayList<Double>> Delta = new ArrayList<>();

        for(int i = 0; i<data.size(); i++) {
            ArrayList<double[]> currentData = data.get(i);
            //Set first layer values
            for(int j = 0; i<vertices.get(1).size(); j++) {
                vertices.get(1).get(j).setValue(currentData.get(0)[j]);
            }
            //Perform forward propagation
            for(int j = 1; j<vertices.size(); j++) {
                for(int k = 0; k<vertices.get(j).size(); k++) {
                    if(!vertices.get(j).get(k).getBias()) {
                        ArrayList<Edge> curEdges = vertices.get(j).get(k).getInputEdges();
                        double z = 0;
                        for(Edge e :  curEdges) {
                            z += e.getWeight()*e.getVertexInput().getValue();
                        }
                        vertices.get(j).get(k).setValue(sigmoid(z));
                    }
                }
            }

            //Calculate deltas
            for(int j = vertices.size()-1; j>=0; j--) {
                for(int k = 0; k<vertices.get(j).size(); k++) {
                    if(!vertices.get(j).get(k).getBias()) {
                        if(j==vertices.size()-1)
                            vertices.get(j).get(k).setDelta(vertices.get(j).get(k).getValue()-currentData.get(1)[k]);
                        else {
                            
                        }
                    }
                }
            }
        }

        return Delta;
    }

    public double sigmoid(double z) {
        return 1/(1+Math.exp(-z));
    }

}
