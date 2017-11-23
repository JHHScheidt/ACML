package lab4;

public class PAVTuple {

    public double position;
    public int action;
    public double velocity;

    public PAVTuple(double pos, int act, double vel) {
        this.position=pos;
        this.action=act;
        this.velocity=vel;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PAVTuple))
            return false;
        else {
            if(this.position==((PAVTuple) obj).position && this.action==((PAVTuple) obj).action && this.velocity==((PAVTuple) obj).velocity)
                return true;
            else return false;
        }
    }
}
