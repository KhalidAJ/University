package Project.interfaces;

import java.util.ArrayList;


public interface IStudent extends IPerson{
    
    double getGpa();
    ArrayList<ICourse> getCourses();
    
    void setGpa(double gpa);
    void setCourses(ArrayList<ICourse> courses);
    
}
