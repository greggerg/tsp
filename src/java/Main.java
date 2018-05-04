import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    private static long seed;
    private static int counter = 0;
    private static int min;

    public static void main(final String[] args) {
        final String[] problems = {"ch130", "d198", "eil76", "fl1577", "kroA100", "lin318", "pcb442", "pr439", "rat783", "u1060"};
        min = Integer.MAX_VALUE;
        for (seed = 0; seed < Long.MAX_VALUE; seed++) {
        final ExecutorService service = Executors.newSingleThreadExecutor();
        Random random = new Random(seed);
//            for (int i = 0; i < problems.length; i++) {
        Problem problem = new Problem(problems[8], seed);
        launchProblem(service, random, problem);
         }
        System.exit(0);
    }

    private static void launchProblem(ExecutorService service, Random random, Problem problem) {
        System.out.println("counter = " + counter++);
        try {
            final Future<Object> f = service.submit(() -> {
                problem.solve(random);
                return "Finished";
            });
            System.out.println(f.get(180, TimeUnit.SECONDS));
        } catch (final TimeoutException e) {
            System.out.println("End of 3 minutes");
            Solution solution = problem.getSolution();
            int distance = solution.getDistance();
            if (distance < min) {
                System.out.println("=====================================================New Min " + min + " " + distance);
                min = distance;
                try {
                    solution.write(problem);
                    solution.writeInfo(problem, seed);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (final Exception e1) {
            throw new RuntimeException(e1);
        } finally {
            service.shutdown();
        }
    }
}
