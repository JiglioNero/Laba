package Para;

import java.util.Objects;

public class Time {
    private int day, hour, min, month, year;

    public Time()
    {
        day = 0; hour = 0; min = 0; year = 0; month = 0;
    }

    public Time(int d, int mo, int y, int h, int m)
    {
        day = d; hour = h; min = m; year = y; month = mo;
    }

    public int getMin()
    {
        return min;
    }

    public int getHour()
    {
        return hour;
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return day == time.day &&
                hour == time.hour &&
                min == time.min &&
                month == time.month &&
                year == time.year;
    }

    @Override
    public int hashCode() {

        return Objects.hash(day, hour, min, month, year);
    }

    @Override
    public String toString()
    {
        return day + "/" + month + "/" + year + " " + min + ":" + hour;
    }
}
