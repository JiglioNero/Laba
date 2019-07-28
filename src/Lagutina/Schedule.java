package Lagutina;

import java.util.TreeSet;

public class Schedule {

    private TreeSet<Lesson> schedule = new TreeSet<>();

    public void addLesson(Lesson lesson) {
        if (lesson.isValid()) {
            for (Lesson l : schedule) {
                if (l.getDay() == lesson.getDay() && l.getNomberOfLesson() == lesson.getNomberOfLesson() && (l.getGroupName().equals(lesson.getGroupName()) || l.getNomberOfRoom() == lesson.getNomberOfRoom())) {
                    System.out.println("Busy!");
                    return;
                }
            }
            schedule.add(lesson);
        }
    }

    public void remove(int day, int nomberOfLesson, String groupName) {
        if (Lesson.dayIsValid(day) && Lesson.groupNameIsValid(groupName) && Lesson.nomberOfLessonIsValid(nomberOfLesson)) {
            Lesson l = getLesson(day, nomberOfLesson, groupName);
            if (l != null)
                schedule.remove(l);
        }
    }

    public Lesson getLesson(int day, int nomberOfLesson, String groupName) {
        if (Lesson.dayIsValid(day) && Lesson.groupNameIsValid(groupName) && Lesson.nomberOfLessonIsValid(nomberOfLesson)) {
            for (Lesson l : schedule) {
                if (l.getDay() == day && l.getNomberOfLesson() == nomberOfLesson && l.getGroupName().equals(groupName)) {
                    return l;
                }
            }
        }
        return null;
    }

    public void printSchedule() {
        for (Lesson l : schedule) {
            System.out.println(l);
        }
    }

    public void printScheduleForGroup(String groupName) {
        for (Lesson l : schedule) {
            if (groupName.equals(l.getGroupName()))
                System.out.println(l);
        }
    }

    public void printScheduleForDay(int day) {
        for (Lesson l : schedule) {
            if (day == l.getDay())
                System.out.println(l);
        }
    }
}
