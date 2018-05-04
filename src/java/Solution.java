import java.io.FileNotFoundException;
import java.io.PrintWriter;

class Solution {
    private City[] cities;
    private int cost;

    public Solution(City[] cities, int cost) {
        this.cities = cities;
        this.cost = cost;
    }

    void write(Problem problem) throws FileNotFoundException {
        if (cities == null) {
            System.out.println("Error");
            return;
        }
        System.out.println("Writing file");
        String name = problem.getName();
        int optimum = problem.getBestKnown();
        int dimension = problem.getDimension();
        String extension = ".opt.tour";

        PrintWriter pw = new PrintWriter("C:\\Users\\meyer\\IdeaProjects\\tsp\\src\\resources\\output\\" + name + extension);
        pw.write("NAME : " + name + extension + "\nCOMMENT : Optimum tour for " + name + " (" + optimum + ")");
        pw.write("\nTYPE : TOUR\nDIMENSION : " + dimension + "\nTOUR_SECTION\n");
        for (City city : cities) {
            pw.write((city.getId() + 1) + " " + city.getX() + " " + city.getY() + "" + "\n");
        }
        pw.write("-1\nEOF");
        pw.close();
    }

    void writeInfo(Problem problem, long seed) throws FileNotFoundException {
        String name = problem.getName();
        PrintWriter pw = new PrintWriter("C:\\Users\\meyer\\IdeaProjects\\tsp\\src\\resources\\output\\" + name + ".seed");
        pw.write("NAME : " + name);
        pw.write("\nPARAM : \n");
        pw.write("\nSEED : " + seed);
        pw.write("\nCOST : " + cost);
        pw.write("\n-1\nEOF");
        pw.close();
    }

    public int getDistance() {
        return cost;
    }
}
