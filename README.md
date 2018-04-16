
# INFO6205_529

Project memeber:
Jin Li  001234402
Chenyang Zhao 001239935

Problem Description:
Realization of Genetic Algorithms for Travelling Salesman Problem

In this project, we solve the travelling salesman problem. We answer the following questions: “Given a list of cities and the coordinates of each pair of cities, what is the shortest possible route that visits each city and returns to the origin city?"; “which chief factors can affect the evolve generation and shortest route of travelling salesman problem?” Through creating genes(city), chromosomes, calculating fitness, evolution (crossover and mutation), acquiring shortest distance and best route, we get the results and conclusions. We discover population size, survive rate, mutation rate and crossover rate are related to the best shortest distance and evolve generation.

We use 4 classes: Chromosome, Gene, Population and GeneticAlgorithm. In Gene class, we define the id and coordinates of each genes. In Chromosome class, we get the genes(cities) route arraylist. The route is a list of different genes(cities) and we define each route as a chromosome. For one chromosome or a pair of chromosomes, we do the clone, crossover and mutation. In Population class, we get a chromosomes arraylist and then evolution. We calculate the distance of each chromosome and acquire the shortest distance of currently generation through priority queue sorting. Finally, we get the shortest distance and best route among every generations. In GeneticAlgorithm class, we initialize the parameters, define the terminal condition. When the shortest distance is stable during ‘x’ generation, we end the program and get ‘evolve generation’ which equals to current generation – x. Then, run the logging function and the program.

Method:
1.	Genes and Chromosomes: Firstly, we create genotype. Genotype is one gene(city) and its coordinate. We create the object gene and specify the gene size. We randomly generate the different id for each gene from 0 to gene size -1 and different coordinates. Then, we define the expression. The expression is that we map all of the genes to a route. Finally, we get the phenotype. Phenotype is a list of all mapped different genes and it is also a route of all created cities. Furthermore, for phenotype, we traverse all genes and map them into a route. In this route, every gene is different. In phenotype, we get the route ArrayList. We regard a route as a chromosome which describes the route or the order of different genes.
2.	Fitness function: We calculate the distance between two adjacent genes. Adding all distances together, we can get the distance of current chromosome. We use the distance of chromosome as the fitness function.
3.	Sort: We create a chromosome ArrayList called population. The ArrayList has every chromosome. Using the priority queue sorting algorithm, we sort the chromosomes of current generation by the fitness(distance). According to the survive rate, we get (survive rate* gene size) chromosomes and use them as parents.
4.	Evolution: We randomly select two parent chromosomes and clone them. According to the crossover rate, if the random number is bigger than crossover rate, we add the cloned chromosomes into next generation’s population ArrayList. If the random number is smaller than crossover rate, we do the crossover.
5.	Crossover: We use 2 parent chromosomes. For example, the route of parent chromosome 1(pc1) is 1-2-3-4-5-6-7. The route of parent chromosome 2(pc2) is 4-1-7-6-5-2-3. Through crossover, we get 2 children chromosomes (cc1 and cc2). Firstly, we get 2 random number, r1 and r2(r1<r2). In this example, r1 = 2 and r2 = 4. We define flag = r2-r1+1. For cc1, the genes from 0 to flag – 1 is the genes of pc2 from r1 to r2. Then, in pc1, we remove the same genes with the first ‘flag’ number added genes of cc1. Furthermore, we add the remaining genes to cc1 from flag to gene size -1 orderly. For cc2, the genes from 0 to flag – 1 is the genes of pc1 from r1 to r2. Then, in pc2, we remove the same genes with the first ‘flag’ number added genes of cc2. Furthermore, we add the remaining genes to cc2 from flag to gene size -1 orderly. In this way, we can get two children chromosomes. The route of cc1 is 7-6-5-1-2-3-4. The route of cc2 is 3-4-5-1-7-6-2.
6.	Mutation: According to the mutation rate, we do the mutation for a chromosome. We randomly select two genes (ids/ cities) and then change their positions.
Before mutation:  1-2-3-4-5-6-7
After mutation:   1-5-3-4-2-6-7
7.	Get best distance: After calculating the fitness function, we can get the shortest distance of each generation. Then, we can get the shortest distance among all generations in order to get the historic best route.
8.	Logging function: We use log to document the shortest distance and best route of each generation into a log file.
