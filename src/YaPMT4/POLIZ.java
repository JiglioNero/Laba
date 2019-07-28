package YaPMT4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class POLIZ {
    private ArrayList<String> data = new ArrayList<>();
    private int currentLine = 0;

    public void interpreter() throws IOException {
        Stack<Identificator> stack = new Stack<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) != null && !data.get(i).isEmpty()) {
                    String[] arr = data.get(i).split(" ");

                    for (int j = 0; j < arr.length; j++) {
                        Identificator a, b;
                        if (ContextConditions.isServiceWord(arr[j])) {
                            switch (arr[j]) {
                                case "+":
                                    stack.push(new Identificator(stack.pop().getValue() + stack.pop().getValue()));
                                    break;
                                case "-":
                                    a = stack.pop();
                                    stack.push(new Identificator(stack.pop().getValue() - a.getValue()));
                                    break;
                                case "*":
                                    stack.push(new Identificator(stack.pop().getValue() * stack.pop().getValue()));
                                    break;
                                case "/":
                                    a = stack.pop();
                                    stack.push(new Identificator(stack.pop().getValue() / a.getValue()));
                                    break;
                                case "and":
                                    stack.push(new Identificator(stack.pop().getValue() * stack.pop().getValue()));
                                    break;
                                case "or":
                                    a = stack.pop();
                                    b = stack.pop();
                                    if (a.getValue() + b.getValue() == 2)
                                        stack.push(new Identificator(1));
                                    else stack.push(new Identificator(a.getValue() + b.getValue()));
                                    break;
                                case "true":
                                    stack.push(new Identificator(1));
                                    break;
                                case "false":
                                    stack.push(new Identificator(0));
                                    break;
                                case "<":
                                    if (stack.pop().getValue() > stack.pop().getValue())
                                        stack.push(new Identificator(1));
                                    else stack.push(new Identificator(0));
                                    break;
                                case ">":
                                    if (stack.pop().getValue() < stack.pop().getValue())
                                        stack.push(new Identificator(1));
                                    else stack.push(new Identificator(0));
                                    break;
                                case "!=":
                                    if (stack.pop().getValue() != stack.pop().getValue())
                                        stack.push(new Identificator(1));
                                    else stack.push(new Identificator(0));
                                    break;
                                case "=":
                                    if (stack.pop().getValue() == stack.pop().getValue())
                                        stack.push(new Identificator(1));
                                    else stack.push(new Identificator(0));
                                    break;
                                case "not":
                                    if (stack.pop().getValue() == 1)
                                        stack.push(new Identificator(0));
                                    else stack.push(new Identificator(1));
                                    break;
                                case ":=":
                                    a = stack.pop();
                                    stack.pop().setValue(a.getValue());
                                    break;
                            }
                        } else {

                            Identificator id = ContextConditions.isId(arr[j]);
                            if (id != null)
                                stack.push(id);
                            else if (ContextConditions.isNumber(arr[j]))
                                stack.push(new Identificator(Integer.parseInt(arr[j])));
                            else if (arr[j].equals("!F")) {
                                a = stack.pop();
                                if (stack.pop().getValue() == 0)
                                    i = a.getValue() - 1;
                            } else if (arr[j].equals("!"))
                                i = stack.pop().getValue() - 1;
                            else if (arr[j].equals("(r)"))
                                stack.pop().setValue(Integer.parseInt(reader.readLine()));
                            else if (arr[j].equals("(w)"))
                                System.out.println(stack.pop().getValue());
                        }
                    }
                }
            }
        }
    }

    public void append(String lex) {
        if (lex.equals("\n")) {
            data.add(new String());
            currentLine++;
            return;
        }
        if (data.size() == 0) {
            data.add(lex + " ");
            return;
        }
        String s = data.get(currentLine);
        s += lex + " ";
        data.remove(currentLine);
        data.add(s);
    }

    public void deleteLine(int index) {
        data.remove(index);
        if (index != currentLine)
            currentLine--;
    }

    public void addLine(String line, int index) {
        data.add(index, line);
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public String getLine(int index) {
        return data.get(index);
    }

    public void setLine(String line, int index) {
        data.remove(index);
        data.add(index, line);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < data.size(); i++) {
            s += i + ") " + data.get(i) + "\n";
        }
        return s;
    }
}
