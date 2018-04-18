import java.util.ArrayList;

public class Utils {

    public static int[][] getDistanceMatrix(ArrayList<City> cities) {
        int[][] matrix = new int[cities.size()][cities.size()];
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                if (i > j) {
                    matrix[i][j] = matrix[j][i] = cities.get(i).calculateDistance(cities.get(j));
                } else if (i == j) {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
       // printMatrix(matrix);
        return matrix;
    }

    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            System.out.println();
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
        }
    }
}
