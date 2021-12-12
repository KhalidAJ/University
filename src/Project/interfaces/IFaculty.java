package Project.interfaces;

import java.util.List;

public interface IFaculty extends IPerson{
    
    double getSalary();
    List<ICourse> getCourses();
    
    void setSalary(double salary);
    void setCourses(List<ICourse> courses);
    
}

