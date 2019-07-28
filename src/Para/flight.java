package Para;

public class flight implements Arrays{
    private int num, price;
    private String type, Apoint, Bpoint;
    private Time date;

    public flight()
    {
        date = new Time(0, 0, 0, 0, 0);
        Apoint = "Neverland"; Bpoint = "Neverland"; num = 0; price = 0; type = "Ilya Myrometz";
    }

    public flight(int n, String ap, String bp, int d, int h, int m, int mo, int y, int p)
    {
        date = new Time(d, mo, y, m, h);
        Apoint = ap; Bpoint = bp; num = n; price = p;
    }

    public flight(String s[])
    {
        date = new Time(Integer.parseInt(s[4]), Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Integer.parseInt(s[8]));
        Apoint = s[2]; Bpoint = s[3]; type = s[1];
        num = Integer.parseInt(s[0]); price = Integer.parseInt(s[9]);
    }

    public void setNumber(int n)
    {
        num = n;
    }

    @Override
    public Time getTime()
    {
        return date;
    }

    @Override
    public String getPointA()
    {
        return Apoint;
    }

    @Override
    public String getPointB()
    {
        return Bpoint;
    }

    @Override
    public boolean equals(Arrays A)
    {
        return date.equals(A.getTime()) && Apoint.equals(A.getPointA()) && Bpoint.equals(A.getPointB());
    }

    @Override
    public String toString()
    {
        return "Номер:" + num + " " + type + " " + Apoint + "-" + Bpoint + " " + date.toString() + " " + price + "$";
    }
}
