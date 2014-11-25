import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by googl_000 on 11/25/2014.
 */
public class Pythagorean5 implements Pythagorean {
    public int solve(int[] sequence)
    {
        int count = 0;
        int[] squared_seq = new int[sequence.length];

        // Square the array
        for (int i = 0; i < squared_seq.length; i++)
        {
            squared_seq[i] = sequence[i] * sequence[i];
        }

        // Identify triples
        for (int ii = 0; ii < squared_seq.length; ii++)
        {
            for (int jj = ii + 1; jj < squared_seq.length; jj++)
            {
                if (Arrays.binarySearch(squared_seq, (squared_seq[ii] + squared_seq[jj])) >= 0)
                {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    public String getName() {
        return "pre-squared with binary search";
    }
}
