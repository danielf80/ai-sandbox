
public class ResultNeuron extends AbstractNeuron {

    private double target;

    public ResultNeuron(int id, double target) {
        super(id);
        this.target = target;
    }

    @Override
    public void calcError() {
        double dOutError = -(this.target - this.outValue);
        double dOutNet = this.outValue * (1 - this.outValue);
        this.errValue = dOutError * dOutNet;
    }

    public double getTarget() {
        return target;
    }
}
