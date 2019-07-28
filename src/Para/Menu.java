package Para;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner in = new Scanner(System.in);

    public int stringToInt(String val) {
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    boolean timeValid(Time t) {
        return !(t.getDay() > 31 || t.getDay() < 1 || t.getMonth() > 12 || t.getMonth() < 1 || t.getMin() > 59 || t.getMin() < 0 || t.getHour() > 23 || t.getHour() < 0 || t.getYear() == -1);
    }

    boolean flightValid(String[] s) {
        if (s.length != 10)
            return false;
        Time date = new Time(stringToInt(s[4]), stringToInt(s[5]), stringToInt(s[6]), stringToInt(s[7]), stringToInt(s[8]));
        int num = stringToInt(s[0]), price = stringToInt(s[9]);
        return timeValid(date) && num > 0 && price > 0;
    }

    boolean requestValid(String[] s) {
        if (s.length != 9)
            return false;
        Time date = new Time(stringToInt(s[4]), stringToInt(s[5]), stringToInt(s[6]), stringToInt(s[7]), stringToInt(s[8]));
        return timeValid(date);
    }

    void Add_flight(ArrayList<flight> A) {
        System.out.println("Введите новый рейс:\n");

        String input = in.nextLine();
        input = in.nextLine();
        String[] data = input.split("\\s+|,\\s*");
        try {
            if (!flightValid(data))
                throw new Exception("Неверный ввод!");

            A.add(new flight(data));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    void Add_request(ArrayList<request> A) {
        System.out.println("Введите новую заявку:\n");

        String input = in.nextLine();
        input = in.nextLine();
        String[] data = input.split("\\s+|,\\s*");

        try {
            if (!requestValid(data))
                throw new Exception("Неверный ввод!");

            A.add(new request(data));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    void Remove(ArrayList A) {
        System.out.println("Введите номер элемента(>0):\n");
        try {
            int index = in.nextInt() - 1;

            if (0 > index || index > A.size() - 1)
                throw new Exception("Неверный индекс!");

            A.remove(index);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    void Print_all(ArrayList<flight> A, ArrayList<request> B) {
        System.out.println("\nРейсы:\n");

        for (int i = 0; i < A.size(); i++)
            System.out.println(A.get(i).toString());

        System.out.println("\nЗаявки:\n");

        for (int i = 0; i < B.size(); i++)
            System.out.println(B.get(i).toString());
    }

    void Search_Flights(ArrayList<flight> F, ArrayList<request> R) {
        for (int i = 0; i < R.size(); i++) {
            int finalI = i;
            F.stream().filter((a) -> R.get(finalI).equals(a)).forEach((a) -> System.out.println(R.get(finalI).toString() + " - " + a.toString() + "\n"));
        }
    }

    void Search_by_date(ArrayList<request> A) {
        int d, m, y, h, min;
        System.out.println("Введите время (d/m/y/h/min) :\n");

        d = in.nextInt();
        m = in.nextInt();
        y = in.nextInt();
        h = in.nextInt();
        min = in.nextInt();
        try {
            Time t = new Time(d, m, y, h, min);

            if (!timeValid(t))
                throw new Exception("Неверное время!");

            A.stream().filter((a) -> a.getTime().equals(t)).forEach((a) -> System.out.println(a.toString()));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    void Search_by_points(ArrayList<flight> A) {
        System.out.println("Введите пункты (Отправления/Назначения) :\n");

        String input = in.nextLine();

        input = in.nextLine();
        String[] point = input.split("\\s+|,\\s*");
        try {
            if (point.length != 2)
                throw new Exception("Неверный ввод!");

            A.stream().filter((a) -> a.getPointA().equals(point[0]) && a.getPointB().equals(point[1]));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    void Remove_by_date(ArrayList<request> A) {
        int d, m, y, h, min;
        System.out.println("Введите время (d/m/y/h/min) :\n");

        d = in.nextInt();
        m = in.nextInt();
        y = in.nextInt();
        h = in.nextInt();
        min = in.nextInt();

        try {
            Time t = new Time(d, m, y, h, min);

            if
                    (!timeValid(t))
                throw new Exception("Неверное время!");

            A.stream().filter((a) -> a.getTime().equals(t)).forEach((a) -> A.remove(a));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    void Start(ArrayList F, ArrayList R) {
        boolean end = false;
        int n;

        System.out.println("\nФормат ввода Рейсов:\n"
                + "Номер/Тип/Пункт А/Пункт Б/День/Месяц/Год/Часы/Минуты/Цена"
                + "\nФормат ввода Заявок:\n"
                + "Имя/Фамилия/Пункт А/Пункт Б/День/Месяц/Год/Часы/Минуты\n");

        System.out.println("\n1)Добавить Рейс\n"
                + "2)Удалить Рейс\n"
                + "3)Добавить Заявку\n"
                + "4)Подобрать Рейсы по Заявкам\n"
                + "5)Вывести все Заявки с заданной датой\n"
                + "6)Вывести все Рейсы по пунктам отправления и назначения\n"
                + "7)Удалить все Заявки с заданной датой\n"
                + "8)Вывести всё\n"
                + "9)Выход\n");

        while (end == false) {

            n = in.nextInt();

            switch (n) {
                case 1:
                    Add_flight(F);
                    break;
                case 2:
                    Remove(F);
                    break;
                case 3:
                    Add_request(R);
                    break;
                case 4:
                    Search_Flights(F, R);
                    break;
                case 5:
                    Search_by_date(R);
                    break;
                case 6:
                    Search_by_points(F);
                    break;
                case 7:
                    Remove_by_date(R);
                    break;
                case 8:
                    Print_all(F, R);
                    break;
                case 9:
                    end = true;
                    break;
                default:
                    System.err.println("Ошибка!\n");
                    break;
            }

        }
    }
}