package org.atiuleneva.dz9;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        List<Student> students = new ArrayList<Student>(Arrays.asList(
                new StudentImpl("Andreev", new ArrayList<Course>(Arrays.asList(new CourseImpl("Math"), new CourseImpl("Java")))),
                new StudentImpl("Ivanov", new ArrayList<Course>(Arrays.asList(new CourseImpl("Math"), new CourseImpl("Java")))),
                new StudentImpl("Petrov", new ArrayList<Course>(Arrays.asList(new CourseImpl("Java"), new CourseImpl("SQL")))),
                new StudentImpl("Sidorov", new ArrayList<Course>(Arrays.asList(new CourseImpl("Math"), new CourseImpl("Java"), new CourseImpl("SQL"), new CourseImpl("Python")))),
                new StudentImpl("Vasileva", new ArrayList<Course>(Arrays.asList(new CourseImpl("Java"), new CourseImpl("English")))),
                new StudentImpl("Borisov", new ArrayList<Course>(Arrays.asList(new CourseImpl("Math"), new CourseImpl("Java")))),
                new StudentImpl("Utkina", new ArrayList<Course>(Arrays.asList(new CourseImpl("Java"), new CourseImpl("English"), new CourseImpl("SQL")))),
                new StudentImpl("Golubev", new ArrayList<Course>(Arrays.asList(new CourseImpl("Java")))),
                new StudentImpl("Medvedeva", new ArrayList<Course>(Arrays.asList(new CourseImpl("Java"), new CourseImpl("SQL")))),
                new StudentImpl("Nikolaev", new ArrayList<Course>(Arrays.asList(new CourseImpl("Math"), new CourseImpl("Java"), new CourseImpl("SQL"), new CourseImpl("Python"), new CourseImpl("English"))))
        ));

        //System.out.println(students.get(3).getAllCourses().size());


        HashMap<String, Course> asd = new HashMap<>();
        for(Student s:students) {
            for(Course c:s.getAllCourses()) {
                asd.put(c.getName(), c);
            }
        }

        for (String key:asd.keySet()) {
            System.out.println(asd.get(key).getName());
        }
        System.out.println("==================================");


        students.stream()
                .flatMap(s -> s.getAllCourses().stream())
                .map(c -> c.getName())
                .distinct()
                .forEach(c -> System.out.println(c));

        System.out.println("====List of most Curious Students====");
        students.stream()
                .sorted(new CuriousComparator())
                .limit(3)
                .forEach(s -> System.out.println(s.getName() + ": " + s.getAllCourses().size()));

        System.out.println("=======================================");

        Course course = new CourseImpl("SQL");
        students.stream()
                .filter(s -> s.hasCourse(course))
                .forEach(s -> System.out.println(s.getName() + " посещает " + course.getName()));
    }
}
