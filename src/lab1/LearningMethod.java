package lab1;

import java.util.ArrayList;

/**
 * An interface for creating weight learning methods for a neural network.
 *
 * @author Marciano Geijeselaers
 * @author Joshua Scheidt
 */
public interface LearningMethod {

    /**
     * Sets the needed data for the learning method.
     *
     * @param data The training data
     * @param edges The edges between the vertices of the neural network
     * @param vertices The vertices of the neural network
     */
    void setData(ArrayList<ArrayList<double[]>> data, ArrayList<ArrayList<Edge>> edges, ArrayList<ArrayList<Vertex>> vertices);

    /**
     * Learns the weights of the neural network.
     */
    void learnWeights(double learningRate, int iterations);

}
