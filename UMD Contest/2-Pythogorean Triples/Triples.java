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
        for (int i = 0; i < sequence.length; i++) {
            int a = sequence[i];
            for (int j = i + 1; j < sequence.length; j++) {
                int b = sequence[j];
                for (int k = j + 1; k < sequence.length; k++) {
                    //System.out.println("Preparing to check: {" + a + " " + b + " " + c + "}" );
                    int c = sequence[k];
                    int max = a;
                    if (b > max) max = b;
                    if (c > max) max = c;
                    int min = a;
                    if (b < min) min = b;
                    if (c < min) min = c;

                    int mid = a + b + c - min - max;
                    if (Math.pow(min, 2) + Math.pow(mid, 2) == Math.pow(max, 2))
                        addTripleToAnswer(min, mid, max);

                }
            }
        /* -------------------- END OF INSERTION --------------------*/
        }
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
