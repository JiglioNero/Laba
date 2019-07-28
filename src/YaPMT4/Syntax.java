package YaPMT4;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Syntax {

    private String s;
    private Scanner scanner;
    private POLIZ poliz;

    public Syntax(String text) {
        text.trim();
        scanner = new Scanner(text);
    }

    public boolean scan() throws Exception {
        if (scanner.hasNext()) {
            s = scanner.next();
            poliz = new POLIZ();
            boolean res = P();
            if (res)
                System.out.println(poliz);
                poliz.interpreter();
            return res;
        } else return false;
    }

    private boolean P() throws Exception {
        if (s.equals("program")) {
            if (scanner.hasNext()) {
                s = scanner.next();
                if (D1()) {
                    poliz.deleteLine(0);
                    if (B()) {
                        return !scanner.hasNext();
                    } else return false;
                } else return false;
            } else return false;
        } else return false;
    }

    private boolean D1() throws Exception {
        if (s.equals("var")) {
            if (scanner.hasNext()) {
                s = scanner.next();
                if (D()) {
                    while (s.equals(";")) {
                        if (scanner.hasNext()) {
                            s = scanner.next();
                            if (!D())
                                break;
                        } else return false;
                    }
                    return true;
                } else return false;
            } else return false;
        } else return false;
    }

    private boolean D() throws Exception {
        if (!ContextConditions.isServiceWord(s)) {
            ContextConditions.decid(s);

            if (C()) {
                while (s.equals(",")) {
                    if (scanner.hasNext()) {
                        s = scanner.next();

                        if (!ContextConditions.isServiceWord(s)) {
                            ContextConditions.decid(s);
                        }else break;

                        if (!C())
                            return false;
                    } else return false;
                }
                if (s.equals(":")) {
                    if (scanner.hasNext()) {
                        s = scanner.next();
                        if (Pattern.compile("int|boolean").matcher(s).matches()) {

                            ContextConditions.confirmType(s);
                            ContextConditions.cleanSStack();

                            if (scanner.hasNext()) {
                                s = scanner.next();
                                return true;
                            } else return false;
                        } else return false;
                    } else return false;
                } else return false;
            } else return false;
        }else return false;
    }

    private boolean B() throws Exception {
        if (s.equals("begin")) {
            if (scanner.hasNext()) {
                s = scanner.next();
                if (S()) {
                    while (s.equals(";")) {

                        poliz.append("\n");

                        if (scanner.hasNext()) {
                            s = scanner.next();
                            if (!S())
                                break;
                        } else return false;
                    }
                    if (s.equals("end")) {
                        if (scanner.hasNext()) {
                            s = scanner.next();
                        }
                        return true;
                    } else return false;
                } else return false;
            } else return false;
        } else return false;
    }

    private boolean S() throws Exception {
        if (s.equals("begin"))
            return B();
        else {
            if (s.equals("read")) {
                if (scanner.hasNext()) {
                    s = scanner.next();
                    if (s.equals("(")) {
                        if (scanner.hasNext()) {
                            s = scanner.next();
                            if (I()) {

                                poliz.append("(r)");

                                if (s.equals(")")) {
                                    if (scanner.hasNext()) {
                                        s = scanner.next();
                                        return true;
                                    } else return false;
                                }else return false;
                            } else return false;
                        } else return false;
                    } else return false;
                } else return false;
            } else {
                if (s.equals("if")) {
                    if (scanner.hasNext()) {
                        s = scanner.next();
                        if (E()) {

                            ContextConditions.checknot();
                            poliz.append("\n");
                            int p1 = poliz.getCurrentLine();
                            poliz.append("\n");
                            poliz.append("!F");
                            poliz.append("\n");

                            if (s.equals("then")) {
                                if (scanner.hasNext()) {
                                    s = scanner.next();
                                    if (S()) {

                                        poliz.append("\n");
                                        poliz.setLine(String.valueOf(poliz.getCurrentLine()), p1);

                                        if (s.equals("else")) {
                                            if (scanner.hasNext()) {
                                                s = scanner.next();

                                                int p2 = poliz.getCurrentLine();
                                                poliz.append("\n");
                                                poliz.append("!");
                                                poliz.append("\n");
                                                poliz.setLine(String.valueOf(poliz.getCurrentLine()), p1);

                                                if (S()) {

                                                    poliz.setLine(String.valueOf(poliz.getCurrentLine() + 1), p2);

                                                    return true;
                                                } else return false;
                                            } else return false;
                                        } else return true;
                                    } else return false;
                                } else return false;
                            } else return false;
                        } else return false;
                    } else return false;
                } else {
                    if (s.equals("while")) {
                        if (scanner.hasNext()) {
                            s = scanner.next();
                            if (E()) {

                                ContextConditions.checknot();
                                poliz.append("\n");
                                int p1 = poliz.getCurrentLine();
                                poliz.append("\n");
                                poliz.append("!F");
                                poliz.append("\n");

                                if (s.equals("do")) {
                                    if (scanner.hasNext()) {
                                        s = scanner.next();

                                        if (S()) {

                                            poliz.append("\n");
                                            poliz.append(String.valueOf(p1 - 1));
                                            poliz.append("\n");
                                            poliz.append("!");
                                            poliz.setLine(poliz.getLine(p1) + String.valueOf(poliz.getCurrentLine() + 1), p1);

                                            return true;
                                        } else return false;
                                    } else return false;
                                } else return false;
                            } else return false;
                        } else return false;
                    } else {
                        if (s.equals("write")) {
                            if (scanner.hasNext()) {
                                s = scanner.next();
                                if (s.equals("(")) {
                                    if (scanner.hasNext()) {
                                        s = scanner.next();
                                        if (E1()) {

                                            poliz.append("(w)");

                                            if (s.equals(")")) {
                                                if (scanner.hasNext()) {
                                                    s = scanner.next();
                                                    return true;
                                                } else return false;
                                            }else return false;
                                        } else return false;
                                    } else return false;
                                } else return false;
                            } else return false;
                        } else {
                            if (I()) {
                                if (s.equals(":=")) {

                                    ContextConditions.spush(String.valueOf(s));

                                    if (scanner.hasNext()) {
                                        s = scanner.next();
                                        if (E1()) {

                                            ContextConditions.checkop();
                                            poliz.append(":=");

                                            return true;
                                        } else return false;
                                    } else return false;
                                } else return false;
                            } else return false;
                        }
                    }
                }
            }
        }
    }

    private boolean E() throws Exception {
        if (E1()) {
            if (s.equals("=") || s.equals("!=") || s.equals("<") || s.equals(">")) {

                ContextConditions.spush(String.valueOf(s));
                String op = s.toString();

                if (scanner.hasNext()) {
                    s = scanner.next();
                    if (E1()) {

                        ContextConditions.checkop();
                        poliz.append(op);

                        return true;
                    } else return false;
                } else return false;
            }
            return true;
        } else return false;
    }

    private boolean E1() throws Exception {
        if (T()) {
            while (s.equals("+") || s.equals("-") || s.equals("or")) {

                ContextConditions.spush(String.valueOf(s));
                String op = s.toString();

                if (scanner.hasNext()) {
                    s = scanner.next();
                    if (op.equals("or")) {
                        if (E()) {

                            ContextConditions.checkop();
                            poliz.append(op);

                        } else return false;
                    } else {
                        if (T()) {

                            ContextConditions.checkop();
                            poliz.append(op);

                        } else return false;
                    }
                } else return false;
            }
            return true;
        } else return false;
    }

    private boolean T() throws Exception {
        if (F()) {
            while (s.equals("*") || s.equals("/") || s.equals("and")) {

                ContextConditions.spush(String.valueOf(s));
                String op = s.toString();

                if (scanner.hasNext()) {
                    s = scanner.next();
                    if (op.equals("and")) {
                        if (E()) {

                            ContextConditions.checkop();
                            poliz.append(op);

                        }
                    } else {
                        if (F()) {

                            ContextConditions.checkop();
                            poliz.append(op);

                        } else return false;
                    }
                } else return false;
            }
            return true;
        } else return false;
    }

    private boolean F() throws Exception {
        if (s.equals("not")) {
            if (scanner.hasNext()) {
                s = scanner.next();
                if (F()) {

                    ContextConditions.checknot();
                    poliz.append("not");

                    return true;
                } else return false;
            } else return false;
        } else {
            if (s.equals("(")) {
                if (scanner.hasNext()) {
                    s = scanner.next();
                    if (E()) {
                        if (s.equals(")")) {
                            if (scanner.hasNext()) {
                                s = scanner.next();
                                return true;
                            } else return false;
                        } else return false;
                    } else return false;
                } else return false;
            } else {
                if (I()) {
                    return true;
                } else {
                    if (N()) {
                        return true;
                    } else {
                        return L();
                    }
                }
            }
        }
    }

    private boolean I() throws Exception {
        return C();
    }

    private boolean N() {
        return R();
    }

    private boolean L() {
        if (s.equals("true") || s.equals("false")) {

            ContextConditions.spush("boolean");
            poliz.append(s);

            if (scanner.hasNext()) {
                s = scanner.next();
            } else return false;
            return true;
        }
        return false;
    }

    private boolean C() throws Exception {
        if (Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*").matcher(s).matches() && !ContextConditions.isServiceWord(s)) {

            ContextConditions.meetId(s);
            poliz.append(s);

            if (scanner.hasNext()) {
                s = scanner.next();
            } else return false;
            return true;
        } else return false;
    }

    private boolean R() {
        if (ContextConditions.isNumber(s)) {

            poliz.append(s);
            ContextConditions.spush("int");

            if (scanner.hasNext()) {
                s = scanner.next();
            } else return false;
            return true;
        }else return false;
    }
}
