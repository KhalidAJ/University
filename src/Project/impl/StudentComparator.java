package Project.impl;

import Project.interfaces.*;
import java.util.Comparator;

public class StudentComparator implements Comparator<IStudent>{

    /**
     * This method compares two student objects by comparing their birth dates.
     * @param student1 - The first student object.
     * @param student2 - The first student object.
     * @return An integer representing the size of the first student compared
     *         to the second student. Bigger if positive, equal if zero, and
     *         smaller if negative.
     */
    @Override
    public int compare(IStudent student1, IStudent student2) {
        //If one of the two objects hasn't been instanciazed, 
        // then it will throw an illegal argument exception
        if ((student1 instanceof Student) &&(student2 instanceof Student)){
            return student1.getBirthDate().compareTo(student2.getBirthDate());
        }
        else throw new IllegalArgumentException("You need to pass a Student object");
    }    
}

