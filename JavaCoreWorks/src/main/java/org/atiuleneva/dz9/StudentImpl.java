package org.atiuleneva.dz9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentImpl implements Student{
    private String name;
    private List<Course> courses;

    public StudentImpl(String name, List<Course> courses){
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public boolean hasCourse(Course course) {
        return courses.stream().anyMatch(c -> c.isEqualCourses(course));
    }
}
