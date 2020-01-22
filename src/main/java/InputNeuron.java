
public class InputNeuron extends AbstractNeuron {

    private double value;

    public InputNeuron(int id) {
        super(id);
    }

    public InputNeuron(int id, double value) {
        super(id);
        this.value = value;
        this.outValue = value;
    }

    public void setValue(double value) {
        this.value = value;
        publish();
    }
}
