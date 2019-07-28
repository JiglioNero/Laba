package YaPMT4;

import java.util.ArrayList;
import java.util.Collections;

public class ServiceWord {
    private String value;
    private ArrayList<String> supportedTypes = new ArrayList<>();

    public ServiceWord(String value, String ... types) {
        this.value = value;
        Collections.addAll(supportedTypes, types);
    }

    public String getValue() {
        return value;
    }

    public boolean isSupportedType(String type){
        if (supportedTypes.contains("all"))
            return true;
        return supportedTypes.contains(type);
    }
}
