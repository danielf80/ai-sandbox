import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Layers {

    private List<Neuron> inputLayers;
    private Map<Integer, List<Neuron>> hiddenLayers;
    private List<Neuron> resultLayers;

    public Layers() {
        inputLayers = new ArrayList<>();
        hiddenLayers = new TreeMap<>();
        resultLayers = new ArrayList<>();
    }

    public List<Neuron> getInputLayers() {
        return inputLayers;
    }

    public Map<Integer, List<Neuron>> getHiddenLayers() {
        return hiddenLayers;
    }

    public List<Neuron> getResultLayers() {
        return resultLayers;
    }
}
