package Para;

public class request implements Arrays{
    private String Apoint, Bpoint, name, surname;
    private Time date;

    public request()
    {
        date = new Time(0, 0, 0, 0, 0);
        Apoint = "Neverland"; Bpoint = "Neverland"; name = "John"; surname = "Doe";
    }

    public request(String n, String s, String ap, String bp, int d, int h, int m, int mo, int y)
    {
        date = new Time(d, mo, y, m, h);
        Apoint = ap; Bpoint = bp; name = n; surname = s;
    }

    public request(String s[])
    {
        date = new Time(Integer.parseInt(s[4]), Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Integer.parseInt(s[8]));
        Apoint = s[2]; Bpoint = s[3];
        name = s[0]; surname = s[1];
    }

    public String getPointA()
    {
        return Apoint;
    }

    public String getPointB()
    {
        return Bpoint;
    }

    public Time getTime()
    {
        return date;
    }

    public boolean equals(Arrays A)
    {
        return date.equals(A.getTime()) && Apoint.equals(A.getPointA()) && Bpoint.equals(A.getPointB());
    }

    public String toString()
    {
        return name + " " + surname + " " + Apoint + "-" + Bpoint + " " + date.toString();
    }
}