import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(final String[] args) {
        long seed;
        final String[] problems = {"ch130", "d198", "eil76", "fl1577", "kroA100", "lin318", "pcb442", "pr439", "rat783", "u1060"};

        for (seed = 0; seed < Long.MAX_VALUE; seed++) {
            final ExecutorService service = Executors.newSingleThreadExecutor();
            Random random = new Random(seed);
//            for (int i = 0; i < problems.length; i++) {
                Problem problem = new Problem(problems[9], seed);
                launchProblem(service, random, problem);
//            }
        }
    }

    private static void launchProblem(ExecutorService service, Random random, Problem problem) {
        try {
            final Future<Object> f = service.submit(() -> {
                problem.solve(random);
                return "Finished";
            });
            System.out.println(f.get(180, TimeUnit.SECONDS));
        } catch (final TimeoutException e) {
            System.out.println("End of 3 minutes");

        }
        catch (final Exception e1) {
            throw new RuntimeException(e1);
        } finally {
            service.shutdown();
        }
    }
}
