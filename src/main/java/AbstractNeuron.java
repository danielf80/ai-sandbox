import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractNeuron implements Neuron {
    protected final int id;

    protected List<Neuron> inbound = new ArrayList<>();
    protected List<Neuron> outbound = new ArrayList<>();
    protected double[] weights;
    protected double[] inValuesErrs;
    protected double[] inValues;

    protected double learningRate = 0.5d;
    protected double bias;
    protected double netValue;
    protected double outValue;
    protected double errValue;

    protected int inputs = 0;
    protected int updatedInputs = 0;

    public AbstractNeuron(int id) {
        this.id = id;
    }

    public void addIn(Neuron neuron, double weight) {

        inbound.add(inputs, neuron);

        inputs++;
        if (inputs == 1) {
            inValues = new double[1];
            weights = new double[1];
            inValuesErrs = new double[1];
        } else {
            inValues = Arrays.copyOf(inValues, inputs);
            weights = Arrays.copyOf(weights, inputs);
            inValuesErrs = Arrays.copyOf(inValuesErrs, inputs);
        }
        weights[inputs-1] = weight;

        neuron.outbound().add(this);
    }

    @Override
    public void valueUpdated(Neuron in, double value) {
        int index = inbound.indexOf(in);
        inValues[index] = value;
        updatedInputs++;
        if (updatedInputs == inputs) {
            netValue = bias;
            for (int c = 0; c < inputs; c++) {
                netValue += weights[c] * inValues[c];
            }

            outValue = 1 / (1 + Math.exp(-netValue));

            System.out.println("Neuron " + this.id + ". Net: " + netValue);
            System.out.println("Neuron " + this.id + ". Out: " + outValue);

            publish();

            updatedInputs = 0;
        }
    }

    public void publish() {
        this.outbound.forEach(neuron -> neuron.valueUpdated(this, outValue));
    }

    @Override
    public double getInErrValue(Neuron neuron) {
        int index = inbound.indexOf(neuron);
        return inValuesErrs[index];
    }

    public void calcError() {
        double dOutError = 0;
        for (Neuron neuron : outbound) {
            dOutError += neuron.getInErrValue(this);
        }
        double dOutNet = outValue * (1 - outValue);
        this.errValue = dOutError * dOutNet;
    }

    @Override
    public void adjustWeight() {
        for (int c = 0; c < this.inputs; c++) {
            double dErrorWeight = errValue * this.inValues[c];
//            System.out.println("Neuron " + this.id + ". Error W" + c + ": " + dErrorWeight);
            this.inValuesErrs[c] = this.weights[c] * errValue;
            this.weights[c] = this.weights[c] - (learningRate * dErrorWeight);
//            System.out.println("Neuron " + this.id + ". Weight-" + c + ": " + weights[c]);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<Neuron> inbound() {
        return inbound;
    }

    @Override
    public List<Neuron> outbound() {
        return outbound;
    }

    @Override
    public double[] getWeights() {
        return weights;
    }

    @Override
    public void setWeight(double[] weights) {
        this.weights = weights;
    }

    @Override
    public double getBias() {
        return bias;
    }

    @Override
    public void setBias(double bias) {
        this.bias = bias;
    }

    @Override
    public double getNetValue() {
        return netValue;
    }

    @Override
    public double getOutValue() {
        return outValue;
    }

    @Override
    public double getErrValue() {
        return errValue;
    }
}
