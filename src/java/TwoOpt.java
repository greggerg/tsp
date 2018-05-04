import java.util.Arrays;

public class TwoOpt {
    private final City[] cities;
    private int distance;

    public TwoOpt(City[] cities, int distance) {
        this.cities = Arrays.copyOf(cities,cities.length);
        this.distance = distance;
    }

    private void reverse(final int from, final int to) {
        for (int i = from, j = to; i < j; i++, j--) {
            swap(i, j);
        }
    }

    private void swap(int i, int j) {
        final City temp = cities[i];
        cities[i] = cities[j];
        cities[j] = temp;
    }

    private int wrap(final int i) {
        return (cities.length + i) % cities.length;
    }
    //ok
    private int calculateCost(final City a, final City b, final City c, final City d) {
        final int ab = Utils.distance(a, b), cd = Utils.distance(c, d);
        final int ac = Utils.distance(a, c), bd = Utils.distance(b, d);
        // disuguaglianza triangolare se entrambi candidati sono piu lunghi non puo migliorare -> ritorno positivo
        if (ab < ac && cd < bd)
            return 1;
        // ritorno la dist
        return (ac + bd) - (ab + cd);
    }

    private void activate(final City a, final City b, final City c, final City d) {
        a.setActive(true);
        b.setActive(true);
        c.setActive(true);
        d.setActive(true);
    }

    private int getGain(final int current, final City currentCity) {
        final int prev = wrap(current - 1);
        final int next = wrap(current + 1);
        final City prevCity = cities[prev];
        final City nextCity = cities[next];
        int i = wrap(current + 1);
        int j = wrap(current + 2);
        while (current != j) {
            final City c = cities[i];
            final City d = cities[j];
            int cost = calculateCost(prevCity, currentCity, c, d);
            //PREV
            if (cost < 0) {
                activate(prevCity, currentCity, c, d);
                reverse(Math.min(prev, i) + 1, Math.max(prev, i));//inverti tutto fra prev+1 e i o viceversa se i > prev
                return cost;
            }
            //NEXT
            cost = calculateCost(currentCity, nextCity, c, d);
            if (cost < 0) {
                activate(currentCity, nextCity, c, d);
                reverse(Math.min(current, i) + 1, Math.max(current, i));
                return cost;
            }
            i = j;
            j = wrap(j + 1);
        }
        return 0;
    }


    public City[] solve() {
        int visited = 0;
        int current = 0;
        while (visited < cities.length) {
            final City city = cities[current];
            if (city.isActive()) {
                final int gain = getGain(current, city);
                if (gain < 0) {
                    current = wrap(current - 1);
                    visited = 0;
                    distance += gain;
                    continue;
                }
                city.setActive(false);
            }
            // Se una citta non è attiva o non trovo mosse spostati avanti
            current = wrap(current + 1);
            visited++;
        }
        //riattivo le citta
        for (int i = 0; i < cities.length; i++) {
            cities[i].setActive(true);
        }
        return cities;
    }

    public int getDistance() {
        return distance;
    }
}