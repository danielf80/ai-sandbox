import java.util.List;

public interface Neuron {
    int getId();
//    boolean isHidden();
    List<Neuron> inbound();
    List<Neuron> outbound();
    double[] getWeights();
    void setWeight(double[] weight);
    double getBias();
    void setBias(double bias);
    double getNetValue();
    double getOutValue();
    void calcError();
    double getErrValue();
    double getInErrValue(Neuron neuron);
    void valueUpdated(Neuron in, double value);
    void adjustWeight();
}
