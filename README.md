
# INFO6205_529

Project memeber:
Jin Li  001234402
Chenyang Zhao 001239935

Problem Description:
Realization of Genetic Algorithms for Travelling Salesman Problem

In this project, we solve the travelling salesman problem. We answer the following questions: “Given a list of cities and the coordinates of each pair of cities, what is the shortest possible route that visits each city and returns to the origin city?"; “which chief factors can affect the evolve generation and shortest route of travelling salesman problem?” Through creating genes(city), chromosomes, calculating fitness, evolution (crossover and mutation), acquiring shortest distance and best route, we get the results and conclusions. We discover population size, survive rate, mutation rate and crossover rate are related to the best shortest distance and evolve generation.

We use 4 classes: Chromosome, Gene, Population and GeneticAlgorithm. In Gene class, we define the id and coordinates of each genes. In Chromosome class, we get the genes(cities) route arraylist. The route is a list of different genes(cities) and we define each route as a chromosome. For one chromosome or a pair of chromosomes, we do the clone, crossover and mutation. In Population class, we get a chromosomes arraylist and then evolution. We calculate the distance of each chromosome and acquire the shortest distance of currently generation through priority queue sorting. Finally, we get the shortest distance and best route among every generations. In GeneticAlgorithm class, we initialize the parameters, define the terminal condition. When the shortest distance is stable during ‘x’ generation, we end the program and get ‘evolve generation’ which equals to current generation – x. Then, run the logging function and the program.
