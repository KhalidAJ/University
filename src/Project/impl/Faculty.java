package Project.impl;

import java.util.ArrayList;
import java.util.List;
import Project.interfaces.ICourse;
import Project.interfaces.IFaculty;
import java.util.Scanner;


public class Faculty extends Person implements IFaculty{

    private List<ICourse> courses;
    private double salary;

    /**
     * Creates a new Faculty object. 
     * @param id - ID of the faculty. Must start with an F followed by 8 numbers. (Ex/ F12345678).
     * @param firstName - First name of the faculty.
     * @param lastName - Last name of the faculty.
     * @param birthDate - Birth date of the faculty.
     * @param salary - Salary of the faculty.
     */
    public Faculty(String id, String firstName, String lastName, Date birthDate, Double salary) {
        //creates an initial Person object
        super("", firstName, lastName, birthDate);
        boolean check = true;
        //This loop will keep going until the ID is entered correctly
        while(check){
            if(!(id.substring(0, 1).matches("F") && id.substring(1).matches("[0-9]+") && id.length() == 9)){
                System.out.println(id + " needs to start with an \"F\" followed by 8 numbers. Try again.");
                Scanner s = new Scanner(System.in);
                id = s.next();
            }
            if(id.substring(0, 1).matches("F") && id.substring(1).matches("[0-9]+") && id.length() == 9){
                check = false;
            }
        }
        super.setId(id);
        setSalary(salary);
        setCourses(new ArrayList<ICourse>());
    }

    /**
     * Gets the salary of the faculty.
     * @return the salary of the faculty object.
     */
    @Override
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the faculty.
     * @param salary - The salary to be set on the faculty. Must be a positive number.
     */
    @Override
    public void setSalary(double salary) {
        if(salary > 0)
            this.salary = salary;
        else{
            System.out.println(salary + " must be a positive number, please try again.");
            Scanner s = new Scanner(System.in);
            setSalary(s.nextDouble());
        }
    }

    /**
     * Gets the courses taught by this faculty.
     * @return the list of courses taught.
     */
    @Override
    public List<ICourse> getCourses() {
        return courses;
    }

    /**
     * Sets the courses taught by a faculty
     * @param courses - the list of courses taught.
     */
    @Override
    public void setCourses(List<ICourse> courses) {
        this.courses = courses;
    }

    /**
     * Gets the faculty object in string mode. 
     * @return The faculty's full name and taught course IDs.
     */
    @Override
    public String toString(){
        String s= super.toString() + ",courses: [";
        //Iterate through the faculty's courses,
        // adding them one by one to the resultant string
        for (ICourse c: courses){
            s=s+c.getId();
        }
        s=s+"]";
        
        return s;
    }
        
}
