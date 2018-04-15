package main;

import java.util.ArrayList;
import java.util.List;

import static main.GeneticAlgorithm.cities;

public class Chromosome {

    public ArrayList<Gene> route;

    public ArrayList<Gene> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Gene> route) {
        this.route = route;
    }

    //initialize a random chromosome
    public Chromosome(int size) {
        if (size <= 0) return;
        initGeneSize(size);
        int num[] = new int[size];
        for (int i = 0; i < size; i++) num[i] = i;
        int temp = size;
        for (int i = 0; i < size; i++) {
            int random = (int) (Math.random() * temp);
            route.add(cities.get(num[random]));
            num[random] = num[temp - 1];
            temp--;
        }
    }

    public Chromosome() {

    }

    public Chromosome(ArrayList<Gene> route) {
        this.route = route;
    }

    private void initGeneSize(int size) {
        if (size <= 0) return;
        route = new ArrayList<Gene>(size);
    }

    //randomly generate offspring
    public static List<Chromosome> crossover(Chromosome p1, Chromosome p2) {
        if (p1 == null || p2 == null) return null;
        if (p1.route == null || p2.route == null) return null;
        if (p1.route.size() != p2.route.size()) return null;

        Chromosome c1 = clone(p1);
        Chromosome c2 = clone(p2);

        int i, j, k, flag;
        int size = p1.route.size();
        //generate crossover position randomly
        int a = ((int) (Math.random() * size)) % size;
        int b = ((int) (Math.random() * size)) % size;
        while (a == b) {
            b = ((int) (Math.random() * size)) % size;
        }
//        System.out.println("a: " + a + " b: " + b);
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        flag = b - a+1;
        //crossover
        for (i = 0, j = a; i < flag; i++, j++) {
            c1.route.set(i, p2.route.get(j));
            c2.route.set(i, p1.route.get(j));
        }
        for (k = 0, j = flag; j < size; ) {
            c1.route.set(j, p1.route.get(k++));
            for (i = 0; i < flag; i++) {
                if (c1.route.get(i).getId() == c1.route.get(j).getId()) {
                    break;
                }
            }
            if (i == flag) {
                j++;
            }
        }
        for (k = 0, j = flag; j < size; ) {
            c2.route.set(j, p2.route.get(k++));
            for (i = 0; i < flag; i++) {
                if (c2.route.get(i).getId() == c2.route.get(j).getId()) {
                    break;
                }
            }
            if (i == flag) {
                j++;
            }
        }

        List<Chromosome> list = new ArrayList<Chromosome>();
        list.add(c1);
        list.add(c2);
        return list;
    }

    private static Chromosome clone(final Chromosome c) {
        if (c == null || c.route == null) {
            return null;
        }

        Chromosome copy = new Chromosome();
        copy.initGeneSize(c.route.size());
        for (int i = 0; i < c.route.size(); i++) {
            copy.route.add(c.route.get(i));
        }
        return copy;
    }

    public void mutation() {
        int size = route.size();
        int position1;
        int position2;
        position1 = (int) (Math.random() * (size));
        position2 = (int) (Math.random() * (size));
//        System.out.println(position1 + " " + position2);
        Gene tmp = route.get(position1);
        route.set(position1, route.get(position2));
        route.set(position2, tmp);
    }

    public double routeDistance() {
        int chroLength = route.size();
        Gene start = route.get(0);
        Gene end = route.get(chroLength - 1);

        double sqrt1 = start.getX() - end.getX();
        double sqrt2 = start.getY() - end.getY();
        double score = Math.sqrt(sqrt1 * sqrt1 + sqrt2 * sqrt2);
        for (int i = 0; i < chroLength - 1; i++) {
            Gene from = route.get(i);
            Gene to = route.get(i + 1);
            double sqrt3 = from.getX() - to.getX();
            double sqrt4 = from.getY() - to.getY();
            double distance = Math.sqrt(sqrt3 * sqrt3 + sqrt4 * sqrt4);
            score = score + distance;
        }

        return score;
    }

}
