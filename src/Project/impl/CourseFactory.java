package Project.impl;
import Project.interfaces.*;

public class CourseFactory implements ICourseFactory{

    /**
     * Calls for the CourseFactory object to create a new course.
     * @param id - The ID of the course.
     * @param title - The title of the course.
     * @param credit - The credits of the course.
     * @return The created course object with the entered parameters.
     */
    @Override
    public ICourse createCourse(String id, String title, int credit) {
        return (ICourse)(new Course(id, title, credit));
    }
    
}
