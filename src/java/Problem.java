import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Problem {
    private final ArrayList<City> original;
    private final String filename;
    private String name;
    private int dimension;
    private int best_known;
    private long seed;

    Problem(String name, long seed) {
        this.seed = seed;
        this.original = new ArrayList<>(2000);
        this.name = name;
        this.filename = "C:\\Users\\greg\\tsp\\src\\resources\\" + name + ".tsp";
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Utils.getDistanceMatrix(original);
    }

    public void solve(Random random) {
        City[] currentTour = original.toArray(new City[0]);
        int currentCost = Utils.cost(currentTour);

        NearestNeighbor nn = new NearestNeighbor(random);
        ArrayList<City> nnTour = nn.solve(original);
        int nnCost = nn.getCost();
        if (nnCost < currentCost) {
            currentTour = nnTour.toArray(new City[0]);
            currentCost = nnCost;
        }
        TwoOpt twoOpt = new TwoOpt(currentTour, currentCost);
        City[] tOptTour = twoOpt.solve();
        if (twoOpt.getDistance() < currentCost) {
            currentCost = twoOpt.getDistance();
            currentTour = tOptTour;
        }
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(random);
        City[] sATour = simulatedAnnealing.solve(currentTour, currentCost);
        int sACost = Utils.cost(sATour);
        if (sACost < currentCost) {
            currentCost = sACost;
            currentTour = sATour;
        }
        Solution solution = new Solution(currentTour, currentCost);
        try {
            solution.write(this);
            solution.writeInfo(this, seed);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            char c = line.charAt(0);
            if (c == 'D') {
                dimension = Integer.parseInt(line.trim().replaceAll("\\D", ""));
            } else if (c == 'B') {
                best_known = Integer.parseInt(line.split(" ")[2]);
            }
            if (Character.isDigit(c)) {
                String[] strings = line.split(" ");
                int id = Integer.parseInt(strings[0]);
                double x = Double.parseDouble(strings[1]);
                double y = Double.parseDouble(strings[2]);
                City city = new City(x, y, id - 1);
                original.add(city);
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public int getBestKnown() {
        return best_known;
    }

    public long getSeed() {
        return seed;
    }

    public String getName() {
        return name;
    }
}
