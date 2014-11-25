/**
 * Created by googl_000 on 11/13/2014.
 */
import java.util.Arrays;
import java.util.Random;
public class PythagoreanSpeed {
    public static void main(String[] args) {
        int passes = 100;
        int[] amounts = {50, 100, 500, 1000};
        Pythagorean[] strategies = {new Pythagorean1(), new Pythagorean2(), new Pythagorean3(), new Pythagorean4(), new Pythagorean5()};
        long[][][] timings = new long[amounts.length][strategies.length][passes + 1];

        for (int amounts_index = 0; amounts_index < amounts.length; amounts_index++) {
            int nTerms = amounts[amounts_index];
            System.out.print("n=" + nTerms + "; Iteration:");
            for (int iter = 1; iter <= passes; iter++) {
                System.out.print(" " + iter);
                int[] numbers = new int[nTerms];
                int lastCount = -1;
                Random rand = new Random();
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = rand.nextInt(nTerms) + 1;
                }

                Arrays.sort(numbers);
                for (int strat_index = 0; strat_index < strategies.length; strat_index++) {
                    Pythagorean strategy = strategies[strat_index];
                    long startTime = System.nanoTime();
                    int count = strategy.solve(numbers);
                    timings[amounts_index][strat_index][iter] = (System.nanoTime() - startTime);
                    System.out.print(".");
                    if (lastCount != -1 && lastCount != count) {
                        System.err.println("count " + count + " did not equal lastCount "
                                + lastCount + " for strategy: " + strategy.getName());
                        break;
                    }

                    lastCount = count;
                }
            }
            System.out.println();
        }
        System.out.println("\nTimings:");
        for (int amounts_index = 0; amounts_index < timings.length; amounts_index++) {
            System.out.println("=======(n = " + amounts[amounts_index] + ")=======");
            for (int strategy_index = 0; strategy_index < timings[amounts_index].length; strategy_index++) {
                long average = 0L;
                for (int timing_index = 0; timing_index < timings[amounts_index][strategy_index].length; timing_index++) {
                    average += timings[amounts_index][strategy_index][timing_index];
                }
                System.out.println("Strategy " + strategies[strategy_index].getName()+ ": " + average / (double)timings[amounts_index][strategy_index].length / 1000000D + " ms.");
            }
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
