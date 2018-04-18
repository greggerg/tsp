import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Problem {
    private static ArrayList<City> cities = new ArrayList<>(2000);
    private final int[][] distanceMatrix;
    private final String filename;

    public Problem(String filename) {
        this.filename = filename;
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=============================FILE READED=============================================");
        this.distanceMatrix = Utils.getDistanceMatrix(cities);
    }

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (Character.isDigit(line.charAt(0))) {
                //TODO try trim
                String[] strings = line.split(" ");
                int id = Integer.parseInt(strings[0]);
                double x = Double.parseDouble(strings[1]);
                double y = Double.parseDouble(strings[2]);
                City c = new City(x, y, id);
                cities.add(c);
            }
        }
    }


    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}
