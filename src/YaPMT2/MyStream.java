package YaPMT2;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 30.10.2017.
 */
public class MyStream extends InputStream {
    private String string;
    private int index = 0;

    public MyStream(String string) {
        this.string = string;
    }

    @Override
    public int read() throws IOException {
        if (index<string.length())
        return string.charAt(index++);
        else try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int available() throws IOException {
        return string.length()-(index+1);
    }
}
