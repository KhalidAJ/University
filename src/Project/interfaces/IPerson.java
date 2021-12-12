package Project.interfaces;

public interface IPerson {
    
    String getId();
    String getFirstName();
    String getLastName();
    IDate getBirthDate();
    
    void setId(String id);
    void setFirstName(String fn);
    void setLastName(String ln);
    void setBirthDate(IDate birthDate);
    
}
