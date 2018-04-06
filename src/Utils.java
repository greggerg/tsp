import java.util.ArrayList;

public class Utils {

    public static int[][] getDistanceMatrix(ArrayList<City> cities) {
        int[][] matrix = new int[cities.size()][cities.size()];
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                if (i > j) {
                    matrix[i][j] = matrix[j][i] = cities.get(i).calculateDistance(cities.get(j));
                }
            }
        }
        return matrix;
    }


}
