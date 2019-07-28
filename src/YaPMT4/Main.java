package YaPMT4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Владыка on 03.12.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String string;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            line += "\n";
            string = line;
            while (!line.isEmpty()) {
                line = reader.readLine();
                if (!line.isEmpty())
                    string += line + "\n";
            }

        Syntax syntax = new Syntax(string);
        syntax.scan();
    }
}
