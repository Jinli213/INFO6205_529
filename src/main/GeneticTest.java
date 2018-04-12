package main;

public class GeneticTest extends GeneticAlgorithm {

    public static final int NUM = 1 << 24;

    public GeneticTest() {
        super(24);
    }

    @Override
    public double changeX(Chromosome chro) {
        return ((1.0 * chro.getNum() / NUM) * 100) + 6;
    }

    @Override
    public double caculateY(double x) {
        return 100 - Math.log(x);
    }

    public static void main(String[] args) {
        GeneticTest test = new GeneticTest();
        test.caculate();
    }
}
