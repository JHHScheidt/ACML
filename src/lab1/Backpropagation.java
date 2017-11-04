package lab1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A learning method which implements the interface: LearningMethod.
 *
 * @see lab1.LearningMethod
 * @author Marciano Geijselaers
 * @author Joshua Scheidt
 */
public class Backpropagation implements LearningMethod {

    /**
     * The list of data objects.
     * The first arraylist are the different datapoints.
     * The second arraylist is always a list of size 2; the input node and the output node
     */
    ArrayList<ArrayList<double[]>> data;
    /**
     * The list of edges.
     * The first arraylist represents the layers between the vertex layers (meaning edges.size = vertives.size-1).
     * The second arraylist represents a list of edges for some layer i.
     */
    ArrayList<ArrayList<Edge>> edges;
    /**
     * The list of vertices.
     * The first arraylist represents the layers of the neural network.
     * The second arraylist represents a list of Vertices of some layer i.
     */
    ArrayList<ArrayList<Vertex>> vertices;

    /**
     * Creates a new Backpropagation weight learning class.
     */
    public Backpropagation() {

    }

    @Override
    public void setData(ArrayList<ArrayList<double[]>> data, ArrayList<ArrayList<Edge>> edges, ArrayList<ArrayList<Vertex>> vertices) {
        this.data = data;
        this.edges = edges;
        this.vertices = vertices;
    }

    @Override
    public void learnWeights(double learningRate, int iterations) {
        for(int i = 0; i<iterations; i++) {
            for(int j = 0; j<edges.size(); j++) {
                for(Edge e : edges.get(j)) {
                    e.setDelta(0.0);
                }
            }
            learningIteration(learningRate);
        }


    }

    /**
     * A single learning iteration over all the data points from the testing data.
     *
     * @param learningRate The learning rate alpha used when changing the current weights
     */
    public void learningIteration(double learningRate) {

        for(int i = 0; i<data.size(); i++) {
            ArrayList<double[]> currentData = data.get(i);

            //Set first layer values
            for(int j = 0; j<currentData.get(0).length; j++) {
                vertices.get(0).get((vertices.get(0).get(0).getBias()?1:0)+j).setValue(currentData.get(0)[j]);
            }

            feedForward();

            backwardPropagation(currentData.get(1));

            //Update weights
            for(int j = 0; j<edges.size();j++) {
                for(Edge e : edges.get(j)) {
                    e.setWeight(e.getWeight()-learningRate*(e.getVertexInput().getValue()*e.getVertexOutput().getDelta()));
                }
            }
        }
    }

    /**
     * Performs the forward propagation of the neural network.
     */
    public void feedForward() {
        //Perform forward propagation CORRECT
        for(int j = 1; j<vertices.size(); j++) {
            for(int k = 0; k<vertices.get(j).size(); k++) {
                if(!vertices.get(j).get(k).getBias()) {
                    double z = 0;
                    for(Edge e :  vertices.get(j).get(k).getInputEdges()) {
                        z += e.getWeight()*e.getVertexInput().getValue();
                    }
                    vertices.get(j).get(k).setValue(sigmoid(z));
                }
            }
        }
    }

    /**
     * Calculates the delta values according to the backward propagation algorithm.
     */
    public void backwardPropagation(double[] output) {
        //Calculate deltas
        for(int j = vertices.size()-1; j>0; j--) {
            for(int k = 0; k<vertices.get(j).size(); k++) {
                if(j==vertices.size()-1)
                    vertices.get(j).get(k).setDelta(vertices.get(j).get(k).getValue()-output[k]);
                else {
                    double errorWeightSum = 0;
                    for(Edge e : vertices.get(j).get(k).getOutputEdges()) {
                        errorWeightSum += e.getWeight() * e.getVertexOutput().getDelta();
                    }
                    vertices.get(j).get(k).setDelta(vertices.get(j).get(k).getValue()*(1-vertices.get(j).get(k).getValue())*errorWeightSum);
                }
            }
        }
    }

    /**
     * Performs a sigmoid function step.
     *
     * @param z Value to be filled in into sigmoid
     * @return The result from the sigmoid function
     */
    public double sigmoid(double z) {
        return 1/(1+Math.exp(-z));
    }

}
