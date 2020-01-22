import java.util.ArrayList;
import java.util.List;

public class NeuronBuilder {

    private final int id;
    private Class<? extends Neuron> neuronClass;

    private double[] weights;
    private double bias;



//    protected List<Neuron> inbound = new ArrayList<>();
    protected List<Neuron> outbound = new ArrayList<>();

    public static NeuronBuilder initInbound(int id) {
        return new NeuronBuilder(id, InputNeuron.class);
    }

    public NeuronBuilder(int id, Class<? extends Neuron> neuronClass) {
        this.id = id;
        this.neuronClass = neuronClass;
    }

}
