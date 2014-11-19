/**
 * Created by googl_000 on 11/13/2014.
 */

//UMD solution
public class Pythagorean1 implements Pythagorean {
    public int solve(int[] sequence)
    {
        int count = 0;
        for(int i = 0; i < sequence.length; i++) {
            for(int j = i+1; j < sequence.length; j++) {
                for(int k = j+1; k < sequence.length; k++) {
                    if ( (sequence[i] * sequence[i] + sequence[j] * sequence[j]) == (sequence[k] * sequence[k]) ) {
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
        return "UMD solution";
    }

    public static void main(String[] args)
    {
        int[] sequence = {3,4,5,12,13,11,11,11,11,11,11,1,1,1,1,1};
        System.out.println(new Pythagorean4().solve(sequence));
    }
}
