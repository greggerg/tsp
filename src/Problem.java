import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Problem {
    private static final String FILENAME = "C:\\Users\\greg\\Algoritmi\\src\\resources\\eil76.tsp";
    private static ArrayList<City> cities = new ArrayList<>(2000);
    public Problem() {
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=============================FILE READED=============================================");
        Utils.getDistanceMatrix(cities);
    }

    public static void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String line;
        while ((line = reader.readLine()) != null) {
            if (Character.isDigit(line.charAt(0))) {
                //TODO try trim
                String str = line.substring(line.indexOf(" ") + 1);
                String[] strings = str.split(" ");
                float x = Float.parseFloat(strings[0]);
                float y = Float.parseFloat(strings[1]);
                cities.add(new City(x, y));
            }
        }
    }

}
