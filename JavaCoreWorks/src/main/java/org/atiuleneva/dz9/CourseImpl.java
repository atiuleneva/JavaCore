package org.atiuleneva.dz9;

public class CourseImpl implements Course{
    private String nameCourse;

    public CourseImpl(String nameCourse){
        this.nameCourse = nameCourse;
    }

    public String getName() {
        return nameCourse;
    }

    public boolean isEqualCourses(Course compare) {
        return nameCourse == compare.getName();
    }
}
