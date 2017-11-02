package lab1;

import java.util.ArrayList;
import java.util.Arrays;

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
        double learningRate = 1;
        for(int i = 0; i<10000; i++) {
            learningIteration(learningRate);
        }


    }

    public void learningIteration(double learningRate) {
        ArrayList<ArrayList<Double>> Delta = new ArrayList<>();

        for(int i = 0; i<data.size(); i++) {
            ArrayList<double[]> currentData = data.get(i);
            //Set first layer values
            for(int j = currentData.size()-1; j>0; j--) {
                vertices.get(0).get(vertices.get(0).size()-1-j).setValue(currentData.get(0)[j]);
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
            for(int j = vertices.size()-1; j>0; j--) {
                for(int k = 0; k<vertices.get(j).size(); k++) {
                    if(!vertices.get(j).get(k).getBias()) {
                        if(j==vertices.size()-1)
                            vertices.get(j).get(k).setDelta(vertices.get(j).get(k).getValue()-currentData.get(1)[k]);
                        else {
                            double errorWeightSum = 0;
                            for(Edge e : vertices.get(j).get(k).getOutputEdges()) {
                                errorWeightSum += e.getWeight() * e.getVertexOutput().getValue();
                            }
                            vertices.get(j).get(k).setDelta(vertices.get(j).get(k).getValue()*(1-vertices.get(j).get(k).getValue())*errorWeightSum);
                        }
                    }
                }
            }

            //Calculate Delta
            for(int j = 0; j<edges.size();j++) {
                for(Edge e : edges.get(j)) {
                    e.setDelta(e.getDelta()+e.getVertexInput().getValue()*e.getVertexOutput().getDelta());
                }
            }
        }
        //Update weights
        double lambda = 1;
        for(int j = 0; j<edges.size();j++) {
            for(Edge e : edges.get(j)) {
                e.setWeight(e.getWeight()-learningRate/data.size()*(e.getDelta()+lambda*e.getWeight()));
            }
        }
    }

    public double sigmoid(double z) {
        return 1/(1+Math.exp(-z));
    }

}
