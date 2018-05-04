import java.util.ArrayList;
import java.util.Random;

class NearestNeighbor {

    private final Random random;
    private ArrayList<City> toVisit;
    private int totalCost;
    private ArrayList<City> tour = new ArrayList<>(2000);

    NearestNeighbor(Random random) {
        this.random = random;
    }


    ArrayList<City> solve(ArrayList<City> cities) {
        this.toVisit = new ArrayList<>(cities);
        int start = random.nextInt(toVisit.size());
        City current = toVisit.get(start);
        while (toVisit.size() > 1) {
            tour.add(current);
            toVisit.remove(current);
            current = getClosestCity(current);
        }
        tour.add(current);
        totalCost += Utils.distance(current, tour.get(0));
        return tour;
    }

    private City getClosestCity(City current) {
        int min = Integer.MAX_VALUE;
        City nearest = null;
        for (City toVisit : toVisit) {
            int dist = Utils.distance(toVisit, current);
            if (dist < min) {
                min = dist;
                nearest = toVisit;
            }
        }
        totalCost += min;
        return nearest;
    }

    int getCost() {
        return totalCost;
    }
}
