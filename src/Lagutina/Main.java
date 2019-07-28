package Lagutina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] a) throws IOException {
        Schedule schedule = new Schedule();

        try (BufferedReader fileReader = new BufferedReader(new FileReader("D:\\OneDrive\\Документы\\Лабное\\Laba\\src\\Lagutina\\data.txt"))) {
            String line = fileReader.readLine();
            while (line != null && !line.isEmpty()) {
                String[] lessonsArgs = line.split(" ");
                schedule.addLesson(new Lesson(lessonsArgs[0], lessonsArgs[1], Integer.parseInt(lessonsArgs[2]), Integer.parseInt(lessonsArgs[3]), Integer.parseInt(lessonsArgs[4])));
                line = fileReader.readLine();
            }
        }

        schedule.printSchedule();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println();
                schedule.printSchedule();
                System.out.println("Input command :\n0) Exit\n1) Add lesson\n2) Remove lesson\n3) Get lesson\n4) Print schedule for a day\n5) Print schedule for a group");
                int command = Integer.parseInt(reader.readLine());

                switch (command) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Input data by enter : lesson name, group name, day, nomber of room, nomber of lesson");
                        schedule.addLesson(new Lesson(reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine())));
                        break;
                    case 2:
                        System.out.println("Input data by enter : day, nomber of lesson, group name");
                        schedule.remove(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), reader.readLine());
                        break;
                    case 3:
                        System.out.println("Input data by enter : day, nomber of lesson, group name");
                        System.out.println(schedule.getLesson(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), reader.readLine()));
                        break;
                    case 4:
                        System.out.println("Input nomber of day");
                        schedule.printScheduleForDay(Integer.parseInt(reader.readLine()));
                        break;
                    case 5:
                        System.out.println("Input name of group");
                        schedule.printScheduleForGroup(reader.readLine());
                        break;
                }
            }
        }
    }
}
