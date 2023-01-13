import hw2.Section;

import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;
public class Test {
    //private static List<String> a;
    static void ml() {

        //reads .txt file and store the content into string array
        /**
         * try {//  Block of code to try}
         * catch(IOException e) {Block of code to handle errors}
         * @param    line    string to store information (in lines) from .txt
         * @param    words   string array to information separately from line (name, meetDays, startTime, and endTime)
         */
        List<String> a = new ArrayList<String>();
        String c = "fefwf", b = "afsggggh";
        a.add(c);
        a.add(b);
        System.out.println(a.size());

        /*try {
            String pathname = "src/courses.txt";
            File file = new File(pathname);
            Scanner sc = new Scanner(file);
            String line = "";
            String[] words = line.split("\\s");;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                words = line.split(" ");
            }
            for (int j = 0; j < 4; j++) {
                System.out.println(words[j]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*try {
            String pathname = "src/courses.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line;
            String[] words;
            //read data from imported text file line by line
            for (int i = 0; i < 5; i ++) {
                line = br.readLine();
                //separate information into name, meetDays, startTime, and endTime into a string array
                words = line.split(" ");
                //test loop
                for (int j = 0; j < 4; j++) {
                    System.out.println(words[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void main( String[] args ) {
        ml();
    }
}
