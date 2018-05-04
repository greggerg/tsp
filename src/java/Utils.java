import java.util.ArrayList;

public class Utils {

    private static int[][] matrix;

    public static void getDistanceMatrix(ArrayList<City> cities) {
        matrix = new int[cities.size()][cities.size()];
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                if (i > j) {
                    matrix[i][j] = matrix[j][i] = cities.get(i).calculateDistance(cities.get(j));
                }
            }
        }
    }

    public static int distance(City from, City to) {
        return matrix[from.getId()][to.getId()];
    }

    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            System.out.println();
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
        }
    }

    public static int cost(City[] tour) {
        int cost = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            int distance = distance(tour[i], tour[i + 1]);
//            System.out.println("Distance from " + i+ " to " + (i + 1) + " = " + distance);
            cost += distance;
        }
        int lastDistance = distance(tour[(tour.length - 1)], tour[0]);
//        System.out.println("lastDistance = " + lastDistance);
        cost += lastDistance;
        return cost;
    }

    public static void printTour(City[] tour) {
        for (City city : tour) {
            System.out.println("city = " + city);
        }
    }

    public static City[] reverse(City[] tour, int from, int to) {
        for (int i = from, j = to; i < j; i++, j--) {
            swap(i, j,tour);
        }
        return tour;
    }
    public static void checkTour(City[] cities) {
        for (int i = 0; i < cities.length; i++) {
            City city = cities[i];
            for (int j = 0; j < cities.length; j++) {
                if (i != j) {
                    City city1 = cities[j];
                    if (city.getId() == city1.getId()) {
                        System.out.println("ERROR");
                        System.exit(-1);
                    }
                }
            }
        }
        //  System.out.println("tour OK");
    }
    private static void swap(int i, int j, City[] tour) {
        final City temp = tour[i];
        tour[i] = tour[j];
        tour[j] = temp;
    }


}
