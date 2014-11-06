import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class TryTest  {

    public static void main(String[] args) {
        String [] testNames = { "my", "01" };
        PrintStream out = System.out;
        for (String n : testNames) {
            try {
                System.out.println("***** TEST FILE " + n + ".in *****");
                System.out.print(fileData(n + ".in"));
                System.out.println("***** OUTPUT FOR " + n + ".in *****");
                String logData = tryTest(n);
                System.setOut(out);
                System.out.print(logData);

                String outData = fileData(n+".out");
                if(logData.equals(outData)) {
                    out.println("***** TEST " + n + " passed!");
                } else {
                    System.out.println("**** CORRECT OUTPUT FOR " + n + ".in *****");
                    System.out.print(outData);
                    out.println("***** TEST " + n + " failed!");
                }
                System.out.println("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String tryTest(String testName) {
        try {
            FileInputStream fin = new FileInputStream(new File(testName + ".in"));
            System.setIn(fin);
            String logName = testName + ".log";
            PrintStream flog = new PrintStream(new FileOutputStream(new File(logName)));
            System.setOut(new PrintStream(flog));

            Triples.main(null);

            return fileData(logName);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static String fileData(String fileName) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner fileScanner = new Scanner(bufferedReader);

        while (fileScanner.hasNextLine())
            stringBuffer.append(fileScanner.nextLine() + "\n");

        fileScanner.close();
        return stringBuffer.toString();
    }


}
