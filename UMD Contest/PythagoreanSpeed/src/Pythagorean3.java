import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by googl_000 on 11/14/2014.
 */
public class Pythagorean3 implements Pythagorean{
    public int solve(int[] sequence)
    {
        int[] squared_seq = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++)
        {
            squared_seq[i] = sequence[i] * sequence[i];
        }
        int count = 0;
        for(int i = 0; i < squared_seq.length; i++) {
            for(int j = i+1; j < squared_seq.length; j++) {
                for(int k = j+1; k < squared_seq.length; k++) {
                    if ( squared_seq[i] + squared_seq[j] == squared_seq[k] ) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
    public String getName()
    {
        return "UMD solution with pre-squared array";
    }

    public static void main(String[] args)
    {
        int[] sequence = {3,4,5,12,13,11,11,11,11,11,11,1,1,1,1,1};
        System.out.println(new Pythagorean4().solve(sequence));
    }
}
