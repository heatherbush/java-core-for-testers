import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 1. Написать функцию, принимающую список Student и возвращающую список уникальных курсов,
        // на которые подписаны студенты.
        // 2. Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных
        // (любознательность определяется количеством курсов).
        // 3. Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,
        // которые посещают этот курс.
        List<Student> students = new ArrayList<>();

        students.add(new Student("Sasha", Arrays.asList(new Course("Physics"), new Course("Chemistry"),
                new Course("Algebra"), new Course("Informatics"), new Course("Biology"))));

        students.add(new Student("Pasha", Arrays.asList(new Course("Physics"), new Course("Chemistry"),
                new Course("Geometry"), new Course("Informatics"))));

        students.add(new Student("Dasha", Arrays.asList(new Course("Physics"), new Course("Chemistry"),
                new Course("Mathematics"))));

        students.add(new Student("Masha", Arrays.asList(new Course("Physics"), new Course("Chemistry"),
                new Course("Astronomy"), new Course("Informatics"),
                new Course("History"), new Course("Philosophy"), new Course("Literature"))));

        students.add(new Student("Lesha", Arrays.asList(new Course("Physics"), new Course("Informatics"),
                new Course("Philosophy"), new Course("Literature"), new Course("Music"),
                new Course("Art history"))));

        Course physics = new Course("Physics");

        uniqueCourses(students);
        theMostCurious(students);
        listOfVisitors(students, physics);
    }

    public static void uniqueCourses(List<Student> students) {
        Set<Course> unique = students.stream()
                .map(s -> s.getCourses())
                .flatMap(f -> f.stream())
                .collect(Collectors.toSet());

        System.out.println("Список уникальных курсов: " + unique);
    }

    public static void theMostCurious(List<Student> students) {
        List<Object> mostCurious = students.stream()
                .sorted((s1, s2) -> s2.getCourses().size() - s1.getCourses().size())
                .limit(3)
                .map(m -> m.getName())
                .collect(Collectors.toList());

        System.out.println("Самые любознательные студенты: " + mostCurious);
    }

    public static void listOfVisitors(List<Student> students, Course course) {
        List<String> visitors = students.stream()
                .filter(s -> s.getCourses().contains(course))
                .map(m -> m.getName())
                .collect(Collectors.toList());

        System.out.println(course + " посещают: " + visitors);
    }
}