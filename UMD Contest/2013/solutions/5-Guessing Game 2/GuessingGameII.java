import java.util.Scanner;
import java.util.Vector;

class GuessResponse {
    int guess;
    int numCircles;
    int numSquares;

    GuessResponse(int g, int nc, int ns) {
        this.guess = g;
        this.numCircles = nc;
        this.numSquares = ns;
    }
}

public class GuessingGameII {

    private static int[] solveGuessinGameI(int secret, int guess) {
        int[] ret = new int[2];
        ret[0] = ret[1] = 0;

        String secret_s = String.format("%04d", secret);
        String guess_s = String.format("%04d", guess);
        boolean[] secret_used = new boolean[4];
        boolean[] guess_used = new boolean[4];

        for(int i = 0; i < 4; i++) {
            secret_used[i] = guess_used[i] = false;
            if(secret_s.charAt(i) == guess_s.charAt(i)) {
                ret[0]++;
                secret_used[i] = guess_used[i] = true;
            }
        }

        /* For each of guess characters that are not used yet, check if there is a match with one in secretword that is not used. */
        for(int i = 0; i < 4; i++) {
            if(!guess_used[i]) {
                for(int j = 0; j < 4; j++) {
                    if(!secret_used[j]) {
                        if(guess_s.charAt(i) == secret_s.charAt(j)) {
                            ret[1]++;
                            secret_used[j] = true;
                            // no real need to update guess_used[i] -- won't be touched again
                            guess_used[i] = true;
                            break;
                        }
                    }
                }
            }
        }

        return ret;
    }

    private static boolean isConsistent(int code, GuessResponse gr) {
        int[] clues = solveGuessinGameI(code, gr.guess);
        return clues[0] == gr.numCircles && clues[1] == gr.numSquares;
    }

    private static boolean isConsistentUMDBad(int code, GuessResponse gr) {
        int numCircles = 0, numSquares = 0;

        String code_s = String.format("%04d", code);
        String guess_s = String.format("%04d", gr.guess);
        boolean[] code_used = new boolean[4];
        boolean[] guess_used = new boolean[4];

        for(int i = 0; i < 4; i++) {
            if(code_s.charAt(i) == guess_s.charAt(i)) {
                numCircles++;
                code_used[i] = guess_used[i] = true;
            }
        }

        /* For each of guess characters that are not used yet, check if there is a match with one in codeword that is not used. */
        for(int i = 0; i < 4; i++) {
            if(!guess_used[i]) {
                for(int j = 0; j < 4; j++) {
                    if(!code_used[j]) {
                        if(guess_s.charAt(i) == code_s.charAt(j)) {
                            numSquares++;
                            code_used[j] = true;
                            // no real need to update guess_used[i] -- won't be touched again
                            guess_used[i] = true;
                            break;
                        }
                    }
                }
            }
        }

        return (numCircles == gr.numCircles) && (numSquares == gr.numSquares);
    }

    static final int CASE_INSUFFICIENT_INFORMATION = 0;
    static final int CASE_SUFFICIENT_TO_GUESS_SECRET = 1;
    static final int CASE_ONE_ADDITIONAL_GUESS_SUFFICIENT = 2;

    private static int[] solveGuessinGameII(GuessResponse[] rounds) 
    {
        int[] ret = new int[2];

        Vector<Integer> consistentSolutions = new Vector<Integer>();

        /* Find all the consistent solutions. */
        for(int code = 0; code <= 9999; code++) {

            boolean consistent = true;

            for(GuessResponse gr : rounds) {
                if(! isConsistent(code, gr)) {
                    consistent = false;
                    break;
                }
            }

            if(consistent) 
                consistentSolutions.add(code);
        }

//        if(consistentSolutions.size() < 25) {
//            for(Integer i : consistentSolutions) {
//                System.out.println(String.format("		%04d", i.intValue()) + " is consistent.");
//            }
//        } else {
//            System.out.println("		More than 25 consistent solutions");
//        }


        if(consistentSolutions.size() == 0) {
            // We have a problem -- this shouldn't happen
            System.out.println("Inconsistent input... Stopping");
            System.exit(1);
        }

        /* If there are more than 25 remaining solutions, we cannot distinguish between them with one more guess. */
        if(consistentSolutions.size() > 25) { // where does the 25 come from??
            ret[0] = CASE_INSUFFICIENT_INFORMATION;
            return ret;
        }

        if(consistentSolutions.size() == 1) {
            ret[0] = CASE_SUFFICIENT_TO_GUESS_SECRET;
            ret[1] = consistentSolutions.get(0);
            return ret;
        }

        /* For each possible guess, check if it can distinguish between the consistent solutions. */
        for(int guess = 0; guess <= 9999; guess++) {
            boolean possible = true;

            boolean response[][] = new boolean[5][5];
            for(int i = 0; i < 4; i++)
                for(int j = 0; j < 4; j++)
                    response[i][j] = false;

            for(Integer i : consistentSolutions) {
                int[] sol = solveGuessinGameI(i, guess);
                if(response[sol[0]][sol[1]]) { // if more than one solution from consistent solutions has the same result - I get the code, but not the reasoning
                    possible = false; // if every single possibility is distinguishable for guess, return guess
                    break;
                } else {
                    response[sol[0]][sol[1]] = true;
                }
            }

            if(possible) {
                ret[0] = CASE_ONE_ADDITIONAL_GUESS_SUFFICIENT;
                ret[1] = guess;
                return ret; // why don't they have to worry about multiple guesses with the same circles/squares? They return on the first guess that works
            }
        }

        ret[0] = CASE_INSUFFICIENT_INFORMATION;
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            int numRounds = sc.nextInt();

            // System.out.print(numRounds + "	");

            GuessResponse[] rounds = new GuessResponse[numRounds];

            for(int j = 0; j < numRounds; j++) {
                rounds[j] = new GuessResponse(sc.nextInt(), sc.nextInt(), sc.nextInt());
                // System.out.print(rounds[j].guess + " " + rounds[j].numCircles + " " + rounds[j].numSquares + "  	");
            }
            // System.out.println();

            int[] ret = solveGuessinGameII(rounds);

            if(ret[0] == CASE_INSUFFICIENT_INFORMATION) {
                System.out.println("There is no guess that can uniquely identify the secret.");
            } else {
                if(ret[0] == CASE_SUFFICIENT_TO_GUESS_SECRET) {
                    System.out.println("The secret is: " + String.format("%04d", ret[1]));
                } else {
                    System.out.println("There is a guess that can uniquely identify the secret: " + String.format("%04d", ret[1]));
                }
            }
        }
    }
}



