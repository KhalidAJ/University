package Project.impl;

import java.util.ArrayList;
import Project.interfaces.*;
import java.util.Scanner;

public class Student extends Person implements IStudent, Comparable<IStudent>{
    
    private ArrayList<ICourse> courses;
    private double gpa;

    /**
     * Creates a new Student object. 
     * @param id - ID of the student. Must start with an S followed by 8 numbers. (Ex/ S12345678).
     * @param firstName - First name of the student.
     * @param lastName - Last name of the student.
     * @param birthDate - Birth date of the student.
     * @param gpa - GPA of the student.
     */
    public Student(String id, String firstName, String lastName, Date birthDate, double gpa){
        //creates an initial Person object
        super("", firstName, lastName, birthDate);
        boolean check = true;
        //This loop will keep going until the ID is entered correctly
        while(check){
            if(!(id.substring(0, 1).matches("S") && id.substring(1).matches("[0-9]+") && id.length() == 9)){
                System.out.println(id + " needs to start with an \"S\" followed by 8 numbers. Try again.");
                Scanner s = new Scanner(System.in);
                id = s.next();
            }
            if(id.substring(0, 1).matches("S") && id.substring(1).matches("[0-9]+") && id.length() == 9){
                check = false;
            }
        }
        super.setId(id);
        this.courses = new ArrayList<ICourse>();
        this.gpa = gpa;
    }

    /**
     * Gets the GPA of the student.
     * @return the GPA of the student object.
     */
    @Override
    public double getGpa() {
        return gpa;
    }

    /**
     * Sets the GPA of the faculty.
     * @param gpa - The GPA to be set on the student. Must be a number between 0 and 4.
     */
    @Override
    public void setGpa(double gpa) {
        if(gpa >= 0 && gpa <= 4)
            this.gpa = gpa;
        else{
            System.out.println(gpa + " needs to be a number from 0 to 4 , please try again.");
            Scanner s = new Scanner(System.in);
            setGpa(s.nextDouble());
        }
    }
    
    /**
     * Gets the courses taken by this student.
     * @return the list of courses taken.
     */
    @Override
    public ArrayList<ICourse> getCourses() {
        return courses;
    }

    /**
     * Sets the courses taken by a student.
     * @param courses - the list of courses taken.
     */
    @Override
    public void setCourses(ArrayList<ICourse> courses) {
        this.courses = courses;
    }

    /**
     * Gets the faculty object in string mode. 
     * @return The faculty's full name and taught course IDs.
     */
    @Override
    public String toString(){
        String s= super.toString() + ", courses: [";
        for (ICourse c: courses){
            s=s+c.getId()+", ";
        }
        s=s+"\b\b]";
        
        return s;
    }

    /**
     * 
     * @param student - the student to be compared to.
     * @return An integer representing the size of the calling object compared
     *         to the specified object. Bigger if positive, equal if zero, and
     *         smaller if negative.
     */
    @Override
    public int compareTo(IStudent student) {
        if(this.getGpa() - ((Student)student).getGpa() > 0){
            return 1;
        }
        else if(this.getGpa() - ((Student)student).getGpa() < 0){
            return -1;
        }
        else
            return 0;
    }
}