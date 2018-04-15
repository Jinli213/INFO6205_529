package test;

import main.Chromosome;
import main.Gene;
import main.Population;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static main.Chromosome.crossover;
import static main.GeneticAlgorithm.cities;
import static main.GeneticAlgorithm.geneSize;
import static org.junit.Assert.assertEquals;

public class MethodTest {

    Chromosome c1 = new Chromosome(geneSize);
    Chromosome c2 = new Chromosome(geneSize);

    @Test
    public void GenerateGene() {
        for (int i = 0; i < geneSize; i++) {
            Gene gene = cities.get(i);
            System.out.print(gene.getId() + " " + gene.getX() + " " + gene.getY());
            System.out.println(" ");
        }

    }

    @Test
    public void GenerateChromosome() {

        for (int i = 0; i < geneSize; i++) {
            System.out.print(c1.route.get(i).getId() + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < geneSize; i++) {
            System.out.print(c2.route.get(i).getId() + " ");
        }
    }

    @Test
    public void CrossoverTest() {
        List<Chromosome> list = crossover(c1, c2);
        System.out.println(" ");
        for (int i = 0; i < geneSize; i++) {
            System.out.print(list.get(0).route.get(i).getId() + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < geneSize; i++) {
            System.out.print(list.get(1).route.get(i).getId() + " ");
        }
    }

    @Test
    public void MutationTest() {
        c1.mutation();
        System.out.println(" ");
        for (int i = 0; i < geneSize; i++) {
            System.out.print(c1.route.get(i).getId() + " ");
        }

    }

    @Test
    public void GeneratePopulation() {

        Population p1 = new Population(10);
        for (int i = 0; i < 10; i++) {
            System.out.println(" ");
            for (int j = 0; j < geneSize; j++) {
                System.out.print(p1.population.get(i).route.get(j).getId() + " ");
            }
        }
    }

    @Test
    public void FitnessTest() {

        ArrayList<Gene> genes = new ArrayList<>();
        genes.add(new Gene(1, 1, 1));
        genes.add(new Gene(2, 4, 5));
        Chromosome c3 = new Chromosome(genes);

        assertEquals((int) c3.routeDistance(), 10);
    }

    @Test
    public void SortTest() {

        ArrayList<Gene> genelist1 = new ArrayList<>();
        ArrayList<Gene> genelist2 = new ArrayList<>();
        genelist1.add(new Gene(1, 10, 10));
        genelist1.add(new Gene(2, 20, 20));
        genelist1.add(new Gene(3, 30, 30));
        Chromosome c4 = new Chromosome(genelist1);
        genelist2.add(new Gene(4, 1, 1));
        genelist2.add(new Gene(5, 2, 2));
        genelist2.add(new Gene(6, 3, 3));
        Chromosome c5 = new Chromosome(genelist2);
        ArrayList<Chromosome> chros = new ArrayList<>();
        chros.add(c4);
        chros.add(c5);
        Population p = new Population(chros);
        ArrayList<Chromosome> result =p.sortRoute(chros);
        for(Chromosome chro: result){
            for(int i =0;i<chro.route.size();i++){
                System.out.print(chro.route.get(i).getId()+" ");
            }
            System.out.print("Distance: "+chro.routeDistance());
            System.out.println();
        }
    }
}
