import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class ShutTheBoxI {

    private static void solveShutTheBoxI(int[] values, int target) {
        boolean[] solution = new boolean[values.length];
        boolean solution_found = false;

        /* ------------------- INSERT CODE HERE ---------------------
         * 
         * Your code should set the values for the variables solution[] and solution_found
         * appropriately.
         *
         * solution_found should be set to true if there is a valid move.
         *
         * the boolean array "solution" should record the best move: if solution[i] = true, 
         * then the corresponding values[i] should be closed.
         *
         * */

        List<Integer> v = shut(values, 0, target);
        solution_found = (v != null);
        if (solution_found) {
            for (int i = 0; i < values.length; i++)
                solution[i] = v.indexOf(values[i]) >= 0;
        }
        /* -------------------- END OF INSERTION --------------------*/

        if(!solution_found) {
            System.out.println("No move found.");
        } else {
            System.out.print("The best move is:");
            for(int i = 0; i < solution.length; i++)
                if(solution[i])
                    System.out.print(" " + values[i]);
            System.out.println();
        }
    }

    public static List<Integer> shut(int[] values, int begin, int target)
    {
        for (int i = values.length - 1; i >= begin; i--)
        {
            if(values[i] < target)
            {
                List<Integer> v = shut(values, i+1, target - values[i]);
                if (v != null)
                {
                    v.add(values[i]);
                    return v;
                }
            } else if (values[i] == target) {
                List<Integer> out = new ArrayList<Integer>();
                out.add(target);
                return out;
            }
        }
        return null;
    }

    public static int sum(List<Integer> v) {
        int sum = 0;
        for (Integer i : v)
        {
            sum += i;
        }
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            int target = sc.nextInt();

            int numEntries = sc.nextInt();

            int[] values = new int[numEntries];

            for(int j = 0; j < values.length; j++)
                values[j] = sc.nextInt();

            solveShutTheBoxI(values, target);
        }
    }
}
