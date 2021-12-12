package Project.impl;

import java.util.Scanner;
import Project.interfaces.*;

public class Course implements ICourse{
   
    private String id;
    private String title;
    private int credit;
    
    /**
     * This method compares the IDs of two courses. 
     * @param course - The course to be compared to.
     * @return An integer representing the size of the calling object compared
     *         to the specified object. Bigger if positive, equal if zero, and
     *         smaller if negative.
     */
    @Override
    public int compareTo(ICourse course) {
        return this.getId().compareTo(course.getId());
    }
    
    /**
     * Creates a new course object.
     * @param id - The ID of the course.
     * @param title - The title of the course.
     * @param credit - The amount of credits the course is worth.
     */
    public Course(String id, String title, int credit) {
        setId(id);
        setTitle(title);
        setCredit(credit);
    }

    /**
     * Gets the course ID of the course.
     * @return The ID of the course.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the course ID of the course. Must only contain capital letters
     * and numbers (Ex/ CHEM101)
     * @param id - The string to be set as the Course ID.
     */
    @Override
    public void setId(String id) {
        //Checks if the input string is made of only capital letters and numbers.
        if(id.matches("[A-Z0-9]+"))
            this.id = id;
        else{
            //If the entered ID contained illegal characters, then it would display the
            // error message, and allow the user to try again.
            System.out.println(id + " needs to only contain capital letters and numbers "
                    + "(ex/ \"CHEM101\"), please input another that follows the format.");
            Scanner s = new Scanner(System.in);
            setId(s.next());
        } 
    }

    /**
     * Gets the title of the course.
     * @return The title of the course.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     * @param title - The string to be set as the title of the course.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the credits of the course.
     * @return The credits of the course.
     */
    @Override
    public int getCredit() {
        return credit;
    }

    /**
     * Sets the credits of the course. Must be a positive integer
     * @param credit - The integer number of credits to be set for the course.
     */
    @Override
    public void setCredit(int credit) {
        //Checks if the entered parameter is greater than zero.
        if(credit > 0)    
            this.credit = credit;
        else{
            //If the entered integer is zero or less, then it would display the error
            // message, and allow the user to try again.
            System.out.println(credit + " needs to only contain positive numbers, "
                    + "please try again.");
            Scanner s = new Scanner(System.in);
            setCredit(s.nextInt());
            }
    } 

    /**
     * Gets the ID of the course as a String
     * @return The ID of the course.
     */
    @Override
    public String toString() {
        return id;
    }
    
    
}

