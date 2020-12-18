package org.atiuleneva.dz9;

import java.util.Comparator;

public class CuriousComparator implements Comparator<Student> {
    public int compare(Student o1, Student o2) {
        return o2.getAllCourses().size() - o1.getAllCourses().size();
    }
}