package YaPMT1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Владыка on 17.10.2017.
 */
public class Condition {
    private HashMap<Pattern,Condition> roots = new HashMap<>();
    private boolean isFinal;

    public Condition(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public void addRoot(Pattern key, Condition condition){
        if (!roots.containsKey(key))
            roots.put(key,condition);
    }

    public boolean check(char[] arr, int index){
        if (arr.length == index && isFinal)
            return true;
        if (index<arr.length) {
            char currentChar = arr[index];
            for (Map.Entry<Pattern,Condition> pair : roots.entrySet())
                if (pair.getKey().matcher(String.valueOf(currentChar)).matches()) {
                    return pair.getValue().check(arr, ++index);
                }
        }
        return false;
    }
}
