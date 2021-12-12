package Project.impl;

import Project.interfaces.*;
import java.util.Scanner;


public class Person implements IPerson{
    
    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    /**
     * Creates a person object. Used for the faculty and student constructors.
     * @param id - The id of the person.
     * @param firstName - The first name of the person.
     * @param lastName - The last name of the person.
     * @param birthDate - The birth date of the person.
     */
    public Person(String id, String firstName, String lastName, Date birthDate) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
    }

    /**
     * Gets the first name of the person.
     * @return the first name of the person.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person. 
     * @param firstName The first name of the person, must only contain letters.
     */
    @Override
    public void setFirstName(String firstName) {
        //checks if the name only contains letters, else it will prompt the user to enter it again.
        if(firstName.matches("[a-zA-Z]+"))
            this.firstName = firstName;
        else{
            System.out.println(firstName + " needs to only contain letters, please input another that follows the format.");
            Scanner s = new Scanner(System.in);
            setFirstName(s.next());
        }
    }

    /**
     * Gets the last name of the person.
     * @return the last name of the person.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person. 
     * @param lastName The last name of the person, must only contain letters.
     */
    @Override
    public void setLastName(String lastName) {
        //checks if the name only contains letters, else it will prompt the user to enter it again.
        if(lastName.matches("[a-zA-Z]+"))
            this.lastName = lastName;
        else{
            System.out.println(lastName + " needs to only contain letters, please input another that follows the format.");
            Scanner s = new Scanner(System.in);
            setLastName(s.next());
        }
    }

    /**
     * Gets the id of the person.
     * @return the id of the person.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the person. 
     * @param id The id of the person.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the person in string format.
     * @return the full name of the person.
     */
    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }

    /**
     * Gets the birth date of the person.
     * @return the birth date of the person.
     */
    @Override
    public IDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the person. 
     * @param birthDate The birth date of the person.
     */
    @Override
    public void setBirthDate(IDate birthDate){
        this.birthDate = (Date)birthDate;
    }
    
}
