package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Population {

    public ArrayList<Chromosome> population;

    public Population(ArrayList<Chromosome> population) {
        this.population = population;
    }

    public Population(int popSize) {

        population = new ArrayList<Chromosome>(popSize);
        for(int i=0;i<popSize;i++){
            population.add(new Chromosome(GeneticAlgorithm.geneSize));
        }
    }

    public void evolution(){

        ArrayList<Chromosome> childrenRoutes = new ArrayList<>();
        while(childrenRoutes.size()<GeneticAlgorithm.popSize) {
            Chromosome parent1 = selectParent();
            Chromosome parent2 = selectParent();

            double random = Math.random();
            if(random<GeneticAlgorithm.crossoverRate) {

                List<Chromosome> children = Chromosome.crossover(parent1, parent2);
                for (Chromosome chro : children) {
                    childrenRoutes.add(chro);
                }
            }
            else if(random<0.9){
                childrenRoutes.add(parent1);
            }
            else{
                childrenRoutes.add(parent2);
            }
        }

        List<Chromosome> t = population;
        population = childrenRoutes;
        t.clear();
        t = null;

        Popmutation();
    }

    private void Popmutation() {
        for(Chromosome chro: population){
            if(Math.random()<GeneticAlgorithm.mutationRate){
                chro.mutation();
            }
        }
    }

    private Chromosome selectParent() {

        int random = (int) (Math.random()*GeneticAlgorithm.popSize/2);
        return sortRoute(population).get(random);
    }

    public ArrayList<Chromosome> sortRoute(ArrayList<Chromosome> p){

        HashMap<Double,Chromosome> hashmap = new HashMap<>();
        ArrayList<Double> list = new ArrayList<>();
        for(Chromosome chro: p){
            list.add(chro.routeDistance());
            hashmap.put(chro.routeDistance(),chro);
        }
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for(Double d : list){
            pq.add(d);
        }
        for(int i =0; i<list.size();i++){
            list.set(i,pq.poll());
        }
        ArrayList<Chromosome> result = new ArrayList<Chromosome>();
        for(int i =0; i<list.size();i++){
            result.add(hashmap.get(list.get(i)));
        }
        return result;
    }


    public Chromosome getBestRoute(ArrayList<Chromosome> sortedRoutes) {

        Chromosome bestRoute = sortedRoutes.get(0);
        return bestRoute;
    }
}
