public class City {
    private float x;
    private float y;

    public City(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int calculateDistance(City city) {
        int distance;
        distance = (int) (Math.sqrt(Math.pow(x - city.x, 2) + Math.pow(y - city.y, 2)) + 0.5);
        return distance;
    }

    @Override
    public String toString() {
        return "City{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
