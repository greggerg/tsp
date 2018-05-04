public class City {
    private double x;
    private double y;
    private int id;
    private boolean active;

    //ok
    public City(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.active = true;
    }

    public int calculateDistance(City city) {
        int distance;
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

    public void setActive(boolean b) {
        this.active = b;
    }

    public boolean isActive() {
        return active;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
