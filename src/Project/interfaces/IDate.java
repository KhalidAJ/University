package Project.interfaces;

public interface IDate extends Comparable<IDate>{
    
    void setYear(int year);
    int getYear();
    void setMonth(int month);
    int getMonth();
    void setDay(int day);
    int getDay();
    String toString();
    
}
