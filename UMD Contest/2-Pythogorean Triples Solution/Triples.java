import java.util.Scanner;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;

public class Triples {

    static Vector<String> triplesFound = null;

    /* Add a triple to the answer so far. Make sure: i < j < k. */
    private static void addTripleToAnswer(int i, int j, int k) {
        String s = "{" + i + " " + j + " " + k + "}";
        triplesFound.add(s);
    }

    private static void solveTriples(int[] sequence) {

        /* ------------------- INSERT CODE HERE --------------------
         *
         * Your code should appropriately call the addTripleToAnswer() function 
         * above for every Pythogorean triple it finds. 
         *
         * */

        /* Given the input sizes, brute force search works. */

        Arrays.sort(sequence);

        for(int i = 0; i < sequence.length; i++) {
            for(int j = i+1; j < sequence.length; j++) {
                for(int k = j+1; k < sequence.length; k++) {
                    if ( (sequence[i] * sequence[i] + sequence[j] * sequence[j]) == (sequence[k] * sequence[k]) ) 
                        addTripleToAnswer(sequence[i], sequence[j], sequence[k]);
                }
            }
        }

        /* -------------------- END OF INSERTION --------------------*/
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {

            int num = sc.nextInt();

            int[] sequence = new int[num];

            for(int j = 0; j < num; j++) 
                sequence[j] = sc.nextInt();

            triplesFound = new Vector<String>();

            solveTriples(sequence);
            
            if(triplesFound.size() == 0) {
                System.out.println("No Pythogorean triples found in the sequence."); 
            } else {

                /* Sort the triples. */
                Collections.sort(triplesFound);

                System.out.print("Found Pythogorean triples: ");
                for(String s : triplesFound) 
                    System.out.print(" " + s);
                System.out.println();
            }
        }
    }
}
