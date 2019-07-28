package YaPMT4;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class ContextConditions {
    private static ArrayList<ServiceWord> serviceWords = new ArrayList<>();
    private static ArrayList<Identificator> identificators = new ArrayList<>();

    static {
        serviceWords.add(new ServiceWord("+", "int"));
        serviceWords.add(new ServiceWord("-", "int"));
        serviceWords.add(new ServiceWord("*", "int"));
        serviceWords.add(new ServiceWord("/", "int"));
        serviceWords.add(new ServiceWord("<", "int"));
        serviceWords.add(new ServiceWord(">", "int"));
        serviceWords.add(new ServiceWord("!=", "int"));
        serviceWords.add(new ServiceWord("=", "int"));
        serviceWords.add(new ServiceWord(":=", "all"));
        serviceWords.add(new ServiceWord("not", "boolean"));
        serviceWords.add(new ServiceWord("and", "boolean"));
        serviceWords.add(new ServiceWord("or", "boolean"));
        serviceWords.add(new ServiceWord("true"));
        serviceWords.add(new ServiceWord("false"));
        serviceWords.add(new ServiceWord("while"));
        serviceWords.add(new ServiceWord("then"));
        serviceWords.add(new ServiceWord("else"));
        serviceWords.add(new ServiceWord("if"));
        serviceWords.add(new ServiceWord("do"));
        serviceWords.add(new ServiceWord("write"));
        serviceWords.add(new ServiceWord("programm"));
        serviceWords.add(new ServiceWord("var"));
        serviceWords.add(new ServiceWord("begin"));
        serviceWords.add(new ServiceWord("end"));
    }

    private static Stack<String> sStack = new Stack<>();
    private static Stack<Identificator> iStack = new Stack<>();

    public static void cleanSStack() {
        sStack.clear();
    }

    public static boolean isServiceWord(String word) {
        for (ServiceWord Sword : serviceWords)
            if (Sword.getValue().equals(word))
                return true;
        return false;
    }

    public static void meetId(String name) throws Exception {
        for (Identificator id : identificators)
            if (id.getName().equals(name)) {
                if (id.isDeclare()) {
                    spush(id.getType());
                    return;
                }
            }
        ERROR();
    }

    public static void decid(String name) throws Exception {
        for (Identificator id : identificators)
            if (id.getName().equals(name) && id.isDeclare())
                ERROR();

        Identificator id = new Identificator(name);
        identificators.add(id);
        id.setDeclare(true);
        ipush(id);
    }

    public static void confirmType(String type){
        while(!iStack.empty()) {
            Identificator id = ipop();
            id.setType(type);
        }
    }

    public static void spush(String string) {
        sStack.push(string);
    }

    public static String spop() {
        return sStack.pop();
    }

    public static void ipush(Identificator id) {
        iStack.push(id);
    }

    public static Identificator ipop() {
        return iStack.pop();
    }

    public static void checkop() throws Exception {
        String t2 = spop();
        String op = spop();
        String t1 = spop();
        String res = gettype(op, t1, t2);
        if (!res.equals("no"))
            spush(res);
        else ERROR();
    }

    public static void checknot() throws Exception {
        if (!spop().equals("boolean"))
            ERROR();
        else
            spush("boolean");
    }


    private static String gettype(String op, String t1, String t2) {
        for (ServiceWord word : serviceWords)
            if (word.getValue().equals(op) && word.isSupportedType(t1) && word.isSupportedType(t2))
                if (Pattern.compile("[<>=]").matcher(op).matches() || op.equals("!="))
                    return "boolean";
                else if (op.equals(":=") && !t1.equals(t2))
                    return "no";
                else if (t1.equals("double") || t2.equals("double"))
                    return "double";
                else if (t1.equals("float") || t2.equals("float"))
                    return "float";
                else return t1;
        return "no";
    }

    public static Identificator isId(String name){
        for (Identificator id : identificators)
            if (id.getName().equals(name))
                return id;
        return null;
    }

    public static boolean isNumber(String s){
        for (char c : s.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }

    private static void ERROR() throws Exception {
        throw new Exception();
    }

}
