package YaPMT2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by User on 30.10.2017.
 */
public class Main {
    static String s;
    static Scanner scanner;


    public static void main(String[] args) throws IOException {
        String string;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String line = reader.readLine();
            line+="\n";
            string = line;
            while(!line.isEmpty()){
                line = reader.readLine();
                if (!line.isEmpty())
                    string+=line+"\n";
            }
        }

        boolean res;
        scanner = new Scanner(string);
        if (scanner.hasNext()) {
            s = scanner.next();
            System.out.println(S());
        }else System.out.println(false);
    }

    public static boolean S(){
            if (s.equals("if")){
                if (scanner.hasNext()) {
                    s = scanner.next();
                    if (E()) {
                            if (s.equals("then")) {
                                if (scanner.hasNext()) {
                                    s = scanner.next();
                                    if (S()) {
                                        if (scanner.hasNext()) {
                                            if (s.equals("else")) {
                                                if (scanner.hasNext()) {
                                                    s = scanner.next();
                                                    return S();
                                                } else return false;
                                            } else return false;
                                        }else return true;
                                    } else return false;
                                } else return false;
                            } else return false;
                    } else return false;
                }else return false;
            }else{
                if (P()){
                        if (s.equals(":=")){
                            if (scanner.hasNext()) {
                                s = scanner.next();
                                return E();
                            }else return false;
                        }else return false;
                }return false;
            }
    }

    public static boolean P() {
        if (s.equals("a")||s.equals("b")){
            if (scanner.hasNext()) {
                s = scanner.next();
                if (s.equals("(")){
                    if (scanner.hasNext()) {
                        s = scanner.next();
                        if (s.equals("e")){
                            if (scanner.hasNext()) {
                                s = scanner.next();
                                if (s.equals(")")) {
                                    if (scanner.hasNext())
                                        s = scanner.next();
                                    return true;
                                } else return false;
                            }else return false;
                        } else return false;
                    }else return false;
                }else return true;
            }else return true;
        }else return false;
    }

    public static boolean E(){
        if (T()){
                while (s.equals("+")) {
                    if (scanner.hasNext()) {
                        s = scanner.next();
                        if (!T())
                            return false;
                    }else return false;
                }
                return true;
        }else return false;
    }

    public static boolean T(){
        if (F()){
                while (s.equals("*")) {
                    if (scanner.hasNext()) {
                        s = scanner.next();
                        if (!F())
                            return false;
                    }else return false;
                }
                return true;
        }else return false;
    }

    public static boolean F(){
        if (s.equals("(")){
            if (scanner.hasNext()){
                s = scanner.next();
                if (E()){
                        if (s.equals(")")){
                            if (scanner.hasNext())
                                s = scanner.next();
                            return true;
                        }else return false;
                }else return false;
            }else return false;
        }else return P();
    }
}
