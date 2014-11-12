import java.util.Scanner;
import java.util.Arrays;

public class VigenereI {

    private static String solveVignereI(String key, String plainText) {
        String cipherText = "";

        /* ------------------- INSERT CODE HERE ---------------------
         *
         * Your code should set "cipherText" to be the encrypted text.
         *
         * */

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++)
        {
            int plainChar = plainText.codePointAt(i) - 'A';
            int keyChar = key.codePointAt(i % key.length()) - 'A';
            sb.append((char)('A' + ((keyChar + plainChar) % 26)));
        }

        cipherText = sb.toString();
        /* -------------------- END OF INSERTION --------------------*/
        return cipherText;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            String key = sc.next();

            String plainText = sc.next();

            System.out.println("Ciphertext: " + solveVignereI(key, plainText));
        }
    }
}
