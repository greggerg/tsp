public class City {
    private double x;
    private double y;
    private int id;


    public City(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;

    }

    public int calculateDistance(City city) {
        int distance;
        //+ 0.5
        distance = (int) (Math.hypot(x - city.x, y - city.y) + 0.5);
        return distance;
    }

    @Override
    public String toString() {
        return "City {id = " + id + " x = " + x + ", y = " + y + '}';
    }

    public int getId() {
        return id;
    }
}
