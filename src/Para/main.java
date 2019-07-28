package Para;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class main
{
    public static void Readflight(ArrayList fList, String[] data) {
        try
        {
            Menu m = new Menu();
            if (!m.flightValid(data))
            {
                throw new Exception("Неверный ввод!");
            }

            fList.add(new flight(data));
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    public static void Readrequest(ArrayList rList, String[] data) {
        try
        {
            Menu m = new Menu();
            if (!m.requestValid(data))
            {
                throw new Exception("Неверный ввод!");
            }

            rList.add(new request(data));
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    public static void Readfiles (ArrayList fList, ArrayList rList) throws Exception
    {
        String s;
        FileReader fr = new FileReader ("src/Pack/flights.txt");
        Scanner in = new Scanner(fr);

        while(in.hasNextLine())
        {
            s = in.nextLine();
            String[] data = s.split("\\s+|,\\s*");
            Readflight(fList, data);
        }

        fr = new FileReader ("src/Pack/requests.txt");
        in = new Scanner(fr);

        while(in.hasNextLine())
        {
            s = in.nextLine();
            String[] data = s.split("\\s+|,\\s*");
            Readrequest(rList, data);
        }
    }

    public static void main(String[] args) throws Exception
    {

        ArrayList <flight> fList = new ArrayList<flight>();
        ArrayList <request> rList = new ArrayList<request>();

        Readfiles(fList, rList);

        Menu mainmenu = new Menu();

        mainmenu.Start(fList, rList);

    }
}
