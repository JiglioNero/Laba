package YaPMT4;

import java.util.Objects;

public class Identificator {
    private String name;
    private boolean declare = false;
    private String type;
    private int value = 0;

    public Identificator(String name) {
        this.name = name;
    }

    public Identificator(int value) {
        this.value = value;
    }

    public void setDeclare(boolean declare) {
        this.declare = declare;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isDeclare() {
        return declare;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
