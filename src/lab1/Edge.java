package lab1;

/**
 * An Edge class which represents a connection between two Vertex objects in a neural network.
 * This class for the connection between all layers.
 *
 * @author Marciano Geijselaers
 * @author Joshua Scheidt
 */
class Edge {

    /** The two Vertex objects which need to be connected */
    private Vertex input, output;
    /** The weights of this edge */
    private double w;

    /**
     * Constructor method which creates a new edge between the given vertices using some weight.
     *
     * @param input The vertex from which the connection is going
     * @param output The vertex to which the connection is going
     * @param w The weight of this edge
     */
    Edge(Vertex input, Vertex output, double w){
        this.input = input;
        this.output = output;
        this.w = w;
    }

    /**
     * Sets the weight of this edge.
     *
     * @param w The new weight of this edge
     */
    void setWeight(double w){
        this.w = w;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return The weight of this edge
     */
    double getWeight(){
        return this.w;
    }

    /**
     * Returns the vertex from which this connection is going.
     *
     * @return The input vertex
     */
    Vertex getVertexInput(){
        return this.input;
    }

    /**
     * Returns the vertex to which this connection is going.
     *
     * @return The output vertex
     */
    Vertex getVertexOutput(){
        return this.output;
    }
}
