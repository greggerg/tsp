import java.util.ArrayList;
import java.util.Random;

public class NearestNeighbor {

    private final Random random;
    private int[][] distanceMatrix;
    private ArrayList<City> toVisit;
    private int totalCost;
    private int start;
    ArrayList<City> tour = new ArrayList<>(2000);

    public NearestNeighbor(Random random) {
        this.random = random;
        this.totalCost = 0;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public ArrayList<City> findBestTour(Problem problem, Random random) {
        this.distanceMatrix = problem.getDistanceMatrix();
        this.toVisit = new ArrayList<>(problem.getCities());
        start = random.nextInt(toVisit.size());
        City current = toVisit.get(start);
        System.out.println("Starting from " + current);
        while (toVisit.size() > 1) {
            //add to tour
            //remove from toVisit
            tour.add(current);
            toVisit.remove(current);
//            if (toVisit.size() == 0) {
//                tour.add(current);
//                break;
//            }
            current = getClosestCity();
            start=current.getId()-1;
        }
        System.out.println(" toVisit " + toVisit.size() +toVisit);
        return tour;
    }

    private City getClosestCity() {
        int min = Integer.MAX_VALUE;
        City nearest = null;
        for (City c : toVisit) {
//devo trovare la distanza minore fra le citta non visitate
            int dist = distanceMatrix[c.getId() - 1][start];
            if (dist < min) {
                min = dist;
                nearest = c;
            }
        }
        System.out.println("nearest = " + nearest);
        System.out.println("dist = " + min);

        totalCost += min;
        return nearest;
    }
}
