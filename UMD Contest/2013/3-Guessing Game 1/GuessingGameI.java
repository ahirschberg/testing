import java.util.Scanner;

public class GuessingGameI {


    private static void solveGuessingGameI(int secret, int guess) {
        int numCircles = 0, numSquares = 0;

        /* ------------------- INSERT CODE HERE ---------------------
         *
         * Your code should set the values of numCircles and numSquares appropriately.
         *
         */
        char[] secretCharArray = Integer.toString(secret).toCharArray();
        String guessStr  = Integer.toString(guess);

        for (int i = 0; i < 4; i++) {
            if (secretCharArray[i] == guessStr.charAt(i)) {
                numCircles++;
                secretCharArray[i] = 'x';
            }
        }

        for (int i = 0; i < 4; i++) {
            char gc = guessStr.charAt(i);
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    char sc = secretCharArray[j];
                    if (gc == sc) {
                        numSquares++;
                        secretCharArray[j] = 'x'; // prevent double counting
                    }
                }

            }
        }
        /* -------------------- END OF INSERTION --------------------*/

        System.out.println("For secret = " + secret + " and guess = " + guess + ", " + numCircles + " circles and " + numSquares + " squares will light up.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {

            int secret = sc.nextInt();
            int guess = sc.nextInt();

            solveGuessingGameI(secret, guess);
        }
    }
}

