import java.util.HashSet;

/**
 * Created by googl_000 on 11/13/2014.
 */
//UMD with math.pow
public class Pythagorean2 implements Pythagorean{
    public int solve(int[] sequence)
    {
        int count = 0;
        for(int i = 0; i < sequence.length; i++) {
            for(int j = i+1; j < sequence.length; j++) {
                for(int k = j+1; k < sequence.length; k++) {
                    if ( Math.pow(sequence[i],2) + Math.pow(sequence[j],2) == Math.pow(sequence[k],2) ) {
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
        return "UMD solution with Math.pow";
    }
}
