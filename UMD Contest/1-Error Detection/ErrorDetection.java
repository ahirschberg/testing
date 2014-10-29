import java.util.Scanner;

public class ErrorDetection {

    /* Given a value and a checkbit, return "true" if the check bit matches
     * up with the "value", and "false" otherwise. */
    private static boolean solveErrorDetection(int value, int checkbit) {
        boolean valid = false;

        /* ------------------- INSERT CODE HERE ---------------------*/
        String binary = get16BitBinary(value);
        int num1s = 0;
        for (int i = 0; i < binary.length(); i++)
        {
            if (binary.charAt(i) == '1')
                num1s++;
        }

        valid = (num1s + checkbit) % 2 == 0;
        /* -------------------- END OF INSERTION --------------------*/

        return valid;
    }

    /* ------------------- ADDITIONAL CODE INSERTED HERE ---------------------*/

    /** Takes an integer, and converts it to a 16 bit integer
     *
     * @param integer
     * @return 16 bit integer as a String
     */
    private static String get16BitBinary(int integer)
    {
        String shortBinary = Integer.toBinaryString(integer);
        StringBuilder missingZeroes = new StringBuilder();
        for (int i = 0; i < (16 - shortBinary.length()); i++)
        {
            missingZeroes.append("0");
        }
        return missingZeroes.toString() + shortBinary;
    }
    /* -------------------- END OF ADDITIONAL INSERTION --------------------*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {

            int value = sc.nextInt();

            int checkbit = sc.nextInt();

            if (solveErrorDetection(value, checkbit)) {
                System.out.println("Valid");
            }
            else {
                System.out.println("Corrupt");
            }
        }
    }
}
