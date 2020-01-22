import java.util.concurrent.atomic.AtomicInteger;

public class Core {

    public static void main(String[] args) {

        final double maxError = 0.00001d;
        AtomicInteger ID = new AtomicInteger(0);
        InputNeuron i1 = new InputNeuron(ID.incrementAndGet(), 0.05);
        InputNeuron i2 = new InputNeuron(ID.incrementAndGet(), 0.10);

        HiddenNeuron h1 = new HiddenNeuron(ID.incrementAndGet());
        HiddenNeuron h2 = new HiddenNeuron(ID.incrementAndGet());

        ResultNeuron o1 = new ResultNeuron(ID.incrementAndGet(), 0.01);
        ResultNeuron o2 = new ResultNeuron(ID.incrementAndGet(), 0.99);

        h1.addIn(i1, 0.15d);
        h1.addIn(i2, 0.20d);
        h1.setBias(0.35d);

        h2.addIn(i1, 0.25d);
        h2.addIn(i2, 0.30d);
        h2.setBias(0.35d);

        o1.addIn(h1, 0.40d);
        o1.addIn(h2, 0.45d);
        o1.setBias(0.60d);

        o2.addIn(h1, 0.50d);
        o2.addIn(h2, 0.55d);
        o2.setBias(0.60d);

        Layers layers = new Layers();
        layers.getInputLayers().add(i1);
        layers.getInputLayers().add(i2);

//        layers.getHiddenLayers().

        int epoch = 0;
        double eTotal = Double.MAX_VALUE;
        while (Math.abs(eTotal) > maxError) {
            if (epoch > 0) {
                o1.adjustWeight();
                o2.adjustWeight();

                h1.calcError();
                h2.calcError();

                h1.adjustWeight();
                h2.adjustWeight();
            } else if (epoch >= 99) {
                System.out.println("Max tries ended");
                break;
            }

            i1.publish();
            i2.publish();

            o1.calcError();
            o2.calcError();

            eTotal = calcError(o1, o2);

            epoch++;
        }
        System.out.println("Epo: " + epoch);
        System.out.println("O-1: " + o1.getOutValue());
        System.out.println("O-2: " + o2.getOutValue());
        System.out.println("Err: " + calcError(o1, o2));
    }

    private static double calcError(ResultNeuron o1, ResultNeuron o2) {
        double e1 = Math.pow(o1.getTarget() - o1.getOutValue(), 2) * 0.5d;
        double e2 = Math.pow(o2.getTarget() - o2.getOutValue(), 2) * 0.5d;
        return e1 + e2;
    }
}
