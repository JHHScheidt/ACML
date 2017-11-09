package lab1;

import java.util.ArrayList;

/**
 * A vertex class which represents a single node in a neural network.
 * This class can be used for all layers and node types.
 *
 * @author Marciano Geijselaers
 * @author Joshua Scheidt
 */
class Vertex {
    /** The value of this vertex */
    private double value = 0.0;
    /** The edges connected to nodes from a previous layer */
    private ArrayList<Edge> inputEdges;
    /** The edges connected to nodes to the next layer */
    private ArrayList<Edge> outputEdges;
    /** The delta value which will be calculated during the backpropagation step */
    private double delta;
    /** The boolean determining whether this vertex is a bias node or not */
    private boolean biased = false;

    /**
     * Constructor method which creates a new vertex without value or bias.
     * This constructor is used for all nodes which are not a bias node or from the input layer.
     */
    Vertex(){
        this.inputEdges = new ArrayList<>();
        this.outputEdges = new ArrayList<>();
    }

    /**
     * Constructor method which creates a new vertex using an existing value and bias.
     * This constructor is used for the bias nodes and input layer.
     *
     * @param value The value of the node
     * @param biased The bias value of the node
     */
    Vertex( double value, boolean biased ) {
        this.value=value;
        this.biased = biased;
        this.inputEdges = new ArrayList<>();
        this.outputEdges = new ArrayList<>();
    }

    /**
     * Adds a new edge to the set of input edges.
     *
     * @param e The to be added edge
     */
    void addInputEdge( Edge e ) {
        this.inputEdges.add(e);
    }

    /**
     * Adds a new edge to the set of output edges.
     *
     * @param e The to be added edge
     */
    void addOutputEdge( Edge e ) {
        this.outputEdges.add(e);
    }

    /**
     * Returns the value of this node.
     *
     * @return The value of this node
     */
    double getValue() {
        return this.value;
    }

    /**
     * Returns the delta value calculated for the backpropagation for this node.
     *
     * @return The backpropagation delta value
     */
    double getDelta() {
        return this.delta;
    }

    /**
     * Sets the value of this node.
     *
     * @param value The new value of this node
     */
    void setValue(double value) {
        this.value=value;
    }

    /**
     * Sets the delta value for the backpropagation for this node.
     *
     * @param delta The new backpropagation delta value
     */
    void setDelta(double delta) {
        this.delta = delta;
    }

    /**
     * Returns the set of input edges going to this node.
     *
     * @return The set of input edges
     */
    ArrayList<Edge> getInputEdges() {
        return this.inputEdges;
    }

    /**
     * Returns the set of output edges going from this node.
     *
     * @return The set of output edges
     */
    ArrayList<Edge> getOutputEdges() {
        return this.outputEdges;
    }

    /**
     * Returns the bias value of this node.
     *
     * @return The bias value
     */
    boolean getBias() {
        return this.biased;
    }
}
