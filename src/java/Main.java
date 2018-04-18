import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

    private static final String FILENAME = "C:\\Users\\greg\\tsp\\src\\resources\\u1060.tsp";

    public static void main(String[] args) {
        Random random = new Random();
        Problem problem = new Problem(FILENAME);
        NearestNeighbor nn = new NearestNeighbor(random);

        ArrayList<City> bestTour = nn.findBestTour(problem, random);
        System.out.println("nn " + nn.getTotalCost());
        System.out.println("================================================================BEST TOUR NN ===============================================");
        City city;

        for (int i = 0; i < bestTour.size(); i++) {
            city = bestTour.get(i);
            for (int j = 0; j < bestTour.size(); j++) {
                if (j != i) {
                    if (city.getId() == bestTour.get(j).getId())
                        System.out.println("ERRORE");
                }
            }
        }
//        for (City c : bestTour) {
//            System.out.println("city = " + c.getId() + " " + c);
//        }

    }
}