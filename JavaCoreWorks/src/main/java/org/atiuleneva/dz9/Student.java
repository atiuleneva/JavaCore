package org.atiuleneva.dz9;

import java.util.ArrayList;
import java.util.List;

public interface Student {
    String getName();
    List<Course> getAllCourses();
    boolean hasCourse(Course course);
}
