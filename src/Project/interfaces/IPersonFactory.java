package Project.interfaces;
import Project.impl.*;

public interface IPersonFactory {
    
    public abstract IPerson getPerson(String type, String id, String firstName, String lastName, Date birthDate, double SalaryOrGpa);
    
}