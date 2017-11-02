package lab1;

public class Vertex {
    private double value;
    private Edge inputEdge;
    private Edge outputEdge;
    private double delta;

    public Vertex( double value ) {
        this.value=value;
    }

    public void addInputEdge( Edge e ) {
        this.inputEdge = e;
    }

    public void addOutputEdge( Edge e ) {
        this.outputEdge = e;
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
}
