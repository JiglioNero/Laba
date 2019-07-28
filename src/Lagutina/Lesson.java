package Lagutina;

import java.util.Objects;

public class Lesson implements Comparable<Lesson> {

    private String lessonName;
    private String groupName;
    private int day;
    private int nomberOfRoom;
    private int nomberOfLesson;

    public Lesson(String lessonName, String groupName, int day, int nomberOfRoom, int nomberOfLesson) {
        this.lessonName = lessonName;
        this.groupName = groupName;
        this.day = day;
        this.nomberOfRoom = nomberOfRoom;
        this.nomberOfLesson = nomberOfLesson;
    }

    public boolean isValid(){
        if (!lessonNameIsValid(lessonName)||
                !groupNameIsValid(groupName)||
                !dayIsValid(day)||
                !nomberOfLessonIsValid(nomberOfLesson)) {
            return false;
        }
        return true;
    }

    public static boolean dayIsValid(int day){
        if (day>7||day<1) {
            System.out.println("Day is not valid!");
            return false;
        }
        return true;
    }

    public static boolean nomberOfLessonIsValid(int nomberOfLesson){
        if (nomberOfLesson>10||nomberOfLesson<1) {
            System.out.println("Nomber of lesson is not valid!");
            return false;
        }
        return true;
    }

    public static boolean lessonNameIsValid(String lessonName){
        if (lessonName==null||lessonName.isEmpty()) {
            System.out.println("Lesson name is not valid!");
            return false;
        }
        return true;
    }

    public static boolean groupNameIsValid(String groupName){
        if (groupName==null||groupName.isEmpty()) {
            System.out.println("Group name is not valid!");
            return false;
        }
        return true;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    @Override
    public String toString() {
        String day = "day";
        switch (this.day) {
            case 1:
                day = "Mondey";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            case 6:
                day = "Saturday";
                break;
            case 7:
                day = "Sunday";
                break;
        }
        return "Lesson{" +
                "lessonName='" + lessonName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", day=" + day +
                ", nomberOfRoom=" + nomberOfRoom +
                ", nomberOfLesson=" + nomberOfLesson +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return day == lesson.day &&
                nomberOfRoom == lesson.nomberOfRoom &&
                nomberOfLesson == lesson.nomberOfLesson &&
                Objects.equals(lessonName, lesson.lessonName) &&
                Objects.equals(groupName, lesson.groupName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lessonName, groupName, day, nomberOfRoom, nomberOfLesson);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getNomberOfRoom() {
        return nomberOfRoom;
    }

    public void setNomberOfRoom(int nomberOfRoom) {
        this.nomberOfRoom = nomberOfRoom;
    }

    public int getNomberOfLesson() {
        return nomberOfLesson;
    }

    public void setNomberOfLesson(int nomberOfLesson) {
        this.nomberOfLesson = nomberOfLesson;
    }

    @Override
    public int compareTo(Lesson o) {
        return (day * 10 + nomberOfLesson) - (o.getDay() * 10 + o.getNomberOfLesson());
    }
}
