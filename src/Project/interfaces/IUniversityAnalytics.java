package Project.interfaces;

import Project.impl.University;
import java.util.List;
import java.util.Map;

public interface IUniversityAnalytics {
    
    Map<ICourse, List<IStudent>> courseStudentList();
    int numberOfStudentInCourse(String CourseID);//CourseID
    Map<String,List<ICourse>> StudentCourseMap();//StudentID, List of CourseIDs
    Map<String,List<String>> StudentInstructorMap();//StudentID, List of FacultyIDs
    Map<String,Double> GpaAverageByCourse();//StudentID, Double
    Map<String,IStudent> BestStudentPerCourse();//CourseID, Student object
    Map<String,Integer> NumberOfStudentsInCourses(int minNumber);//CourseID, Integer
    
}
