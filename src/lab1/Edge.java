package lab1;

/**
 * An Edge class which represents a connection between two Vertex objects in a neural network.
 * This class for the connection between all layers.
 *
 * @author Marciano Geijeselaers
 * @author Joshua Scheidt
 */
public class Edge {

    /** The two Vertex objects which need to be connected */
    private Vertex input, output;
    /** The weights of this edge */
    private double w;
    /** The delta value calculated in the backpropagation */
    private double Delta = 0;

    /**
     * Constructor method which creates a new edge between the given vertices using some weight.
     *
     * @param input The vertex from which the connection is going
     * @param output The vertex to which the connection is going
     * @param w The weight of this edge
     */
    public Edge(Vertex input, Vertex output, double w){
        this.input = input;
        this.output = output;
        this.w = w;
    }

    /**
     * Sets the value of the backpropagation delta value.
     *
     * @param Delta The new backpropagation delta value
     */
    public void setDelta(double Delta){
        this.Delta = Delta;
    }

    /**
     * Sets the weight of this edge.
     *
     * @param w The new weight of this edge
     */
    public void setWeight(double w){
        this.w = w;
    }

    /**
     * Returns the backpropagation delta value of this edge.
     *
     * @return The backpropagation delta value of this edge
     */
    public double getDelta(){
        return this.Delta;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return The weight of this edge
     */
    public double getWeight(){
        return this.w;
    }

    /**
     * Returns the vertex from which this connection is going.
     *
     * @return The input vertex
     */
    public Vertex getVertexInput(){
        return this.input;
    }

    /**
     * Returns the vertex to which this connection is going.
     *
     * @return The output vertex
     */
    public Vertex getVertexOutput(){
        return this.output;
    }
}
