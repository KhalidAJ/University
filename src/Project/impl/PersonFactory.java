package Project.impl;
import Project.interfaces.*;

public class PersonFactory implements IPersonFactory{

    /**
     * Calls for the CourseFactory object to create a new course.
     * @param type - The type of person ("student" or "faculty").
     * @param id - The ID of the person.
     * @param firstName - The first name of the person.
     * @param lastName - The last name of the person.
     * @param birthDate - The birth date of the person.
     * @param SalaryOrGpa - The salary of the faculty or GPA of the student.
     * @return The created person object with the entered parameters.
     */
    @Override
    public IPerson getPerson(String type, String id, String firstName,
            String lastName, Date birthDate, double SalaryOrGpa){
        //If the type is "student", then it will create a Student object with a GPA,
        if(type.equalsIgnoreCase("student")){
            return (IPerson)(new Student(id, firstName, lastName, birthDate, SalaryOrGpa));
        }
        //If the type is "faculty", then is will create a Faculty object with a salary instead,
        if(type.equalsIgnoreCase("faculty")){
            return (IPerson)(new Faculty(id, firstName, lastName, birthDate, SalaryOrGpa));
        }
        //If the type is none of these two, then it will throw an illegal argument exception.
        else throw new IllegalArgumentException("Type of Person object not supported by PersonFactory!");
    }

}
