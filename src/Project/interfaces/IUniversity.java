package Project.interfaces;

import java.util.List;

public interface IUniversity { 
 
    public abstract String getName();
    public abstract List<IPerson> getAffiliates();
    public abstract List<ICourse> getCourses();
    
    public abstract void setName(String name);
    public abstract void setAffiliates(List<IPerson> affiliates);
    public abstract void setCourses(List<ICourse> courses);
    
    public abstract void loadStudents(String fileName);
    public abstract void loadFaculty(String fileName);
    public abstract void loadCourses(String fileName);
    
    public abstract int getNumberOfStudents();
    public abstract int getNumberOfFaculty();
    public int getNumberOfCourses();
    
    public ICourse findCourse(String course);
    public abstract void addCourse(ICourse c);
    public abstract void removeCourse(ICourse c);
    public abstract boolean courseExist(ICourse c);
    
}