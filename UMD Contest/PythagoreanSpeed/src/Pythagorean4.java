import java.util.HashSet;

/**
 * Created by googl_000 on 11/14/2014.
 */
public class Pythagorean4 implements Pythagorean {
    public int solve(int[] sequence)
    {
        int count = 0;
        int[] squared_seq = new int[sequence.length];
        for (int i = 0; i < squared_seq.length; i++)
        {
            squared_seq[i] = sequence[i] * sequence[i];
        }
        HashSet<Integer> seq_hash = new HashSet<Integer>(sequence.length * 10);
        for (int value : squared_seq)
        {
            seq_hash.add(value);
        }
        //System.out.println(seq_hash.contains(169));
        for (int ii = 0; ii < squared_seq.length; ii++)
        {
            for (int jj = ii + 1; jj < squared_seq.length; jj++)
            {
                if (seq_hash.contains(squared_seq[ii] + squared_seq[jj]))
                {
                    count++;
                }
            }
        }
        return count;
    }

    public String getName()
    {
        return "pre-squared with hash";
    }

    public static void main(String[] args)
    {
        int[] sequence = {1,2,3,4,5,12,13,14,15};
        System.out.println(new Pythagorean4().solve(sequence));
    }
}
