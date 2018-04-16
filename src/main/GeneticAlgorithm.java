package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneticAlgorithm {

    public static int popSize = 500;
    public static int geneSize = 30;
    public static int geneRange = 1000;
    public static int maxIterNum = 1000;
    public static int stableGeneration = 50;
    public static double surviveRate = 0.5;
    public static double crossoverRate = 0.8;
    public static double mutationRate = 0.1;



    public GeneticAlgorithm(int geneSize) {
        this.geneSize = geneSize;
    }


    public static ArrayList<Gene> cities = GeneticAlgorithm.generateCities();

    public GeneticAlgorithm() {

    }

    public static ArrayList<Gene> generateCities() {
        Random rand = new Random(100);

        ArrayList<Gene> cities = new ArrayList<Gene>();
        for (int i = 0; i < geneSize; i++) {
            int randx = rand.nextInt(geneRange);
            int randy = rand.nextInt(geneRange);
            int id = i + 1;       //set id for each city
            cities.add(new Gene(id, randx, randy));
        }

        return cities;
    }

    public static void logging() throws IOException {
        Logger log = Logger.getLogger("tsp");
        log.setLevel(Level.INFO);
        FileHandler fileHandler = new FileHandler("EvolutionTrack.log");
        fileHandler.setLevel(Level.INFO);
        log.setUseParentHandlers(false);
        log.addHandler(fileHandler);

        Population p = new Population(popSize);
        double minDistance = Double.MAX_VALUE;
        int changeTime = 0;
        String ss = null;
        String bestgeneration =null;

        for (int i = 0; i < maxIterNum && changeTime < stableGeneration; i++) {
            p.evolution();
            String currentgeneration = String.valueOf(i);

            StringBuffer sb = new StringBuffer();
            StringBuffer bestRoute = new StringBuffer();


            double currentBest = p.getBestRoute(p.population).routeDistance();
            if (currentBest < minDistance) {

                for (int j = 0; j < geneSize; j++) {
                    bestRoute.append(p.getBestRoute(p.population).route.get(j).getId() + "-");
                }
                bestRoute.append(p.getBestRoute(p.population).route.get(0).getId());

                ss = bestRoute.toString();
                bestgeneration=String.valueOf(i);

                minDistance = currentBest;
                changeTime = 0;
            } else {
                changeTime++;
            }
            String finalDistance = String.valueOf(minDistance);
            String bestDistance = String.valueOf(p.getBestRoute(p.population).routeDistance());
            for (int j = 0; j < geneSize; j++) {
                sb.append(p.getBestRoute(p.population).route.get(j).getId() + "-");
            }
            sb.append(p.getBestRoute(p.population).route.get(0).getId());
            log.info("The " + currentgeneration + " generation. Current bestRoute is: " + sb.toString() + " Best Distance is " + bestDistance);
            log.info("The best route in history: " + bestgeneration + " generation. " + ss+" The best distance in history: " + finalDistance);

        }

    }

    public static void main(String[] args) throws IOException {

//        logging();

        Population p = new Population(popSize);
        double minDistance = Double.MAX_VALUE;
        int changeTime = 0;
        String ss = null;
        String bestgeneration = null;

        for (int i = 0; i < maxIterNum && changeTime < stableGeneration; i++) {
            p.evolution();
            String currentgeneration = String.valueOf(i);

            StringBuffer sb = new StringBuffer();
            StringBuffer bestRoute = new StringBuffer();


            double currentBest = p.getBestRoute(p.population).routeDistance();
            if (currentBest < minDistance) {

                for (int j = 0; j < geneSize; j++) {
                    bestRoute.append(p.getBestRoute(p.population).route.get(j).getId() + "-");
                }
                bestRoute.append(p.getBestRoute(p.population).route.get(0).getId());

                ss = bestRoute.toString();
                bestgeneration = String.valueOf(i);

                minDistance = currentBest;
                changeTime = 0;
            } else {
                changeTime++;
            }
            String finalDistance = String.valueOf(minDistance);
            String bestDistance = String.valueOf(p.getBestRoute(p.population).routeDistance());
            for (int j = 0; j < geneSize; j++) {
                sb.append(p.getBestRoute(p.population).route.get(j).getId() + "-");
            }
            sb.append(p.getBestRoute(p.population).route.get(0).getId());
            System.out.println("The " + currentgeneration + " generation. Current bestDistance is " + bestDistance);
//            System.out.println("Curren bestRoute is: "+sb.toString());
            System.out.println("Best route in history: " + bestgeneration + " generation. Best distance in history: " + finalDistance);
//            System.out.println("Best route in history is "+ss);
        }
    }

}
