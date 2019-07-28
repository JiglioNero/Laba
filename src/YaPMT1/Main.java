package YaPMT1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Владыка on 17.10.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> listOfWords = new ArrayList<>();

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(new FileInputStream(r.readLine()));
        while (scanner.hasNextLine())
            Collections.addAll(listOfWords, scanner.nextLine().split(" "));


        Condition start = createAutomatAndGetStart();

        for (String s : listOfWords)
            if(checkWord(start, s))
                System.out.println(s);

    }

    public static boolean checkWord(Condition startCondition, String word){
        return startCondition.check(word.toCharArray(),0);
    }

    public static Condition createAutomatAndGetStart(){
        Condition start = new Condition(false);
        Condition D = new Condition(false);
        Condition C3 = new Condition(false);
        Condition C2 = new Condition(false);
        Condition C1 = new Condition(false);
        Condition B2 = new Condition(false);
        Condition B1 = new Condition(false);
        Condition A2 = new Condition(false);
        Condition A1 = new Condition(true);
        Condition S = new Condition(true);

        Pattern digit = Pattern.compile("[0-9]");
        Pattern alpha = Pattern.compile("[ABEKMHOPCTYXabekmhopctyx]");

        start.addRoot(alpha,D);
        D.addRoot(digit,C3);
        C3.addRoot(digit,C2);
        C2.addRoot(digit,C1);
        C1.addRoot(alpha,B2);
        B2.addRoot(alpha,B1);
        B1.addRoot(digit,A2);
        A2.addRoot(digit,A1);
        A1.addRoot(digit,S);

        return start;
    }
}
