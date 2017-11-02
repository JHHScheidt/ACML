package lab1;

import java.util.ArrayList;

public interface LearningMethod {

    public void setData(ArrayList<ArrayList<double[]>> data, ArrayList<ArrayList<Edge>> edges, ArrayList<ArrayList<Vertex>> vertices);

    public void learnWeights();

}
