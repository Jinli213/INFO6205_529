package main;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneticAlgorithm {

    private List<Chromosome> population = new ArrayList<Chromosome>();
    private int popSize = 100;
    private int geneSize = 50;
    private int maxIterNum = 500;
    private double mutationRate = 0.01;
    private int maxMutationNum = 3;

    private int generation = 1;

    private double bestScore;
    private double worstScore;
    private double totalScore;
    private double avgScore;

    private double x;
    private double y;
    private int geneI;

    public GeneticAlgorithm(int geneSize) {
        this.geneSize = geneSize;
    }

    public void caculate() {
        generation = 1;
        init();
        while (generation < maxIterNum) {
            evolve();
            display();
            generation++;
        }
    }

    private void display() {
        System.out.println("--------------------------------");
        System.out.println("the generation is:" + generation);
        System.out.println("the best y is:" + bestScore);
        System.out.println("the worst fitness is:" + worstScore);
        System.out.println("the average fitness is:" + avgScore);
        System.out.println("the total fitness is:" + totalScore);
        System.out.println("geneI:" + geneI + "\tx:" + x + "\ty:" + y);
    }

    private void evolve() {
        List<Chromosome> childPopulation = new ArrayList<Chromosome>();
        while (childPopulation.size() < popSize) {
            Chromosome p1 = inheritParentChromosome();
            Chromosome p2 = inheritParentChromosome();
            List<Chromosome> children = Chromosome.genetic(p1, p2);
            if (children != null) {
                for (Chromosome chro : children) {
                    childPopulation.add(chro);
                }
            }
        }
        //new population replace old one
        List<Chromosome> t = population;
        population = childPopulation;
        t.clear();
        t = null;

        //genetic mutaiton in new population
        mutation();
        //calculate the fitness of new population
        caculateScore();
    }

    private void mutation() {
        for (Chromosome chro : population) {
            if (Math.random() < mutationRate) {
                int mutationNum = (int) (Math.random() * maxMutationNum);
                chro.mutation(mutationNum);
            }
        }
    }

    private Chromosome inheritParentChromosome() {
        double slice = Math.random() * totalScore;
        double sum = 0;
        for (Chromosome chro : population) {
            sum += chro.getScore();
            if (sum > slice && chro.getScore() >= avgScore) {
                return chro;
            }
        }
        return null;
    }

    private void init() {
        for (int i = 0; i < popSize; i++) {
            population = new ArrayList<Chromosome>();
            Chromosome chro = new Chromosome(geneSize);
            population.add(chro);
        }
        caculateScore();
    }

    private void caculateScore() {
        chromosomeScore(population.get(0));
        bestScore = population.get(0).getScore();
        worstScore = population.get(0).getScore();
        totalScore = 0;
        for (Chromosome chro : population) {
            chromosomeScore(chro);
            if (chro.getScore() > bestScore) {
                bestScore = chro.getScore();
                if (y < bestScore) {
                    x = changeX(chro);
                    y = bestScore;
                    geneI = generation;
                }
            }
            if (chro.getScore() < worstScore) {
                worstScore = chro.getScore();
            }
            totalScore += chro.getScore();
        }
        avgScore = totalScore / popSize;
        avgScore = avgScore > bestScore ? bestScore : avgScore;
    }

    public abstract double changeX(Chromosome chro);
    public abstract  double caculateY(double x);

    private void chromosomeScore(Chromosome chro) {
        if (chro == null) return;
        double x = changeX(chro);
        double y = caculateY(x);
        chro.setScore(y);
    }

    public void setPopulation(List<Chromosome> population) {
        this.population = population;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public void setGeneSize(int geneSize) {
        this.geneSize = geneSize;
    }

    public void setMaxIterNum(int maxIterNum) {
        this.maxIterNum = maxIterNum;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void setMaxMutationNum(int maxMutationNum) {
        this.maxMutationNum = maxMutationNum;
    }

    public double getBestScore() {
        return bestScore;
    }

    public double getWorstScore() {
        return worstScore;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}