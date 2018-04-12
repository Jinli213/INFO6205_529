package main;

import java.util.ArrayList;
import java.util.List;

public class Chromosome {
    private boolean[] gene;
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Chromosome(int size) {
        if (size <= 0) return;
        initGeneSize(size);
        for (int i = 0; i < size; i++) {
            gene[i] = Math.random() >= 0.5;
        }
    }

    public Chromosome() {

    }

    private void initGeneSize(int size) {
        if (size <= 0) return;
        gene = new boolean[size];
    }

    //randomly generate offspring
    public static List<Chromosome> genetic(Chromosome p1, Chromosome p2) {
        if (p1 == null || p2 == null) return null;
        if (p1.gene == null || p2.gene == null) return null;
        if (p1.gene.length != p2.gene.length) return null;

        Chromosome c1 = clone(p1);
        Chromosome c2 = clone(p2);

        int size = c1.gene.length;
        //generate crossover postion randomly
        int a = ((int) (Math.random() * size)) % size;
        int b = ((int) (Math.random() * size)) % size;
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        //crossover
        for (int i = min; i <= max; i++) {
            boolean tmp = c1.gene[i];
            c1.gene[i] = c2.gene[i];
            c2.gene[i] = tmp;
        }

        List<Chromosome> list = new ArrayList<Chromosome>();
        list.add(c1);
        list.add(c2);
        return list;
    }

    private static Chromosome clone(final Chromosome c) {
        if (c == null || c.gene == null) {
            return null;
        }

        Chromosome copy = new Chromosome();
        copy.initGeneSize(c.gene.length);
        for (int i = 0; i < c.gene.length; i++) {
            copy.gene[i] = c.gene[i];
        }
        return copy;
    }

    // mutated in num gene randomly
    public void mutation(int num) {
        int size = gene.length;
        for (int i = 0; i < num; i++) {
            int at = ((int) (Math.random() * size)) % size;
            boolean bool = !gene[at];
            gene[at] = bool;
        }

    }

    //transfer gene to corresponding number
    public int getNum() {
        if (gene == null) {
            return 0;
        }
        int num = 0;
        for (boolean bool : gene) {
            num <<= 1;
            if (bool) {
                num += 1;
            }
        }
        return num;
    }
}
