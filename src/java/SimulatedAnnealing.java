import java.util.Random;

public class SimulatedAnnealing {
    private final Random random;
    //     1_000_000_000;
    private double temp = 1_000_000_000;
    //    0.00001;
    private static final double coolingRate = 0.00001;

    public Solution getSolution() {
        return solution;
    }

    private Solution solution;

    public SimulatedAnnealing(Random random) {
        this.random = random;
    }

    //controllare
    public City[] solve(City[] tour, int originalCost) {
        City[] bestTour = tour;
        int bestDistance = originalCost;
        int currentDistance;
        while (temp > 1) {
            currentDistance = Utils.cost(tour);
            City[] adjacent = doubleBridge(tour);
            int neighbourDistance = Utils.cost(adjacent);
            //2opt
            TwoOpt twoOpt = new TwoOpt(adjacent, neighbourDistance);
            City[] twoOptTour = twoOpt.solve();
            int twoOptCost = twoOpt.getDistance();
            if (twoOptCost < neighbourDistance) {
                adjacent = twoOptTour;
                neighbourDistance = twoOptCost;
            }
            if (isAccepted(currentDistance, neighbourDistance)) {
                tour = adjacent;
                currentDistance = neighbourDistance;
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance;
                    bestTour = tour;
                    solution=new Solution(bestTour,bestDistance);

                            System.out.println("NUOVO percorso minimo " + bestDistance);
                }
            }
            temp *= 1 - coolingRate;
        }
        return bestTour;
    }

    private City[] doubleBridge(City[] tour) {
        int size = tour.length;
        int a = random.nextInt(size / 4 - 1) + 1;
        int b = random.nextInt(size / 4 - 1) + 1 + a;
        int c = random.nextInt(size / 4 - 1) + 1 + b;
        City[] newTour = new City[tour.length];
        //       0/A
        for (int i = 0; i < a; i++) {
            newTour[i] = tour[i];
        }
        //ca
        int ca = size - c;
        int current = a;
        for (int i = 0; i < ca; i++) {
            newTour[current + i] = tour[c + i];
        }
        current += ca;
        int bc = c - b;
        for (int i = 0; i < bc; i++) {
            newTour[current + i] = tour[b + i];
        }
        current += bc;
        //ab
        int ab = b - a;
        for (int i = 0; i < ab; i++) {
            newTour[current + i] = tour[a + i];
        }
        return newTour;
    }

    //ok
    private boolean isAccepted(int currentDistance, int newDistance) {
        if (newDistance < currentDistance) {
            return true;
        }
        return Math.exp((currentDistance - newDistance) / temp) > random.nextDouble();
    }
}
