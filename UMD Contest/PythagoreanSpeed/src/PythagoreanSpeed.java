/**
 * Created by googl_000 on 11/13/2014.
 */
import java.util.Arrays;
import java.util.Random;
public class PythagoreanSpeed {
    public static void main(String[] args) {
        int numPasses = 30;
        Pythagorean[] strategies = {new Pythagorean1(), new Pythagorean2(), new Pythagorean3(), new Pythagorean4()};
        long[][] timings = new long[strategies.length][numPasses + 1];

        System.out.print("Iteration...");
        for (int iter = 1; iter <= numPasses; iter++) {
            System.out.print(iter + " ");
            int[] numbers = new int[1000];
            int lastCount = -1;
            Random rand = new Random();
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = rand.nextInt(1000) + 1;
            }

            Arrays.sort(numbers);
            for (int strat_index = 0; strat_index < strategies.length; strat_index++) {
                Pythagorean strategy = strategies[strat_index];
                long startTime = System.currentTimeMillis();
                int count = strategy.solve(numbers);
                timings[strat_index][iter] = (System.currentTimeMillis() - startTime);

                if (lastCount != -1 && lastCount != count) {
                    System.err.println("count " + count + " did not equal lastCount "
                            + lastCount + " for strategy: " + strategy.getName());
                    break;
                }

                lastCount = count;
            }
        }

        System.out.println("\nTimings:");
        for (int strategy_index = 0; strategy_index < timings.length; strategy_index++)
        {
            long average = 0L;
            for (int timing_index = 0; timing_index < timings[strategy_index].length; timing_index++)
            {
                average += timings[strategy_index][timing_index];
            }
            System.out.println("Strategy " + strategies[strategy_index].getName() + ": " + average / timings[strategy_index].length + " ms.");
        }
    }

    // Unused - for debugging
    public static void printarr(int[] array) {
        StringBuilder sb = new StringBuilder().append(array[0]);
        for (int i = 1; i < array.length; i++)
            sb.append(", ").append(array[i]);
        System.out.println(sb.toString());
    }
}
