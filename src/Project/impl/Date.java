package Project.impl;
import Project.interfaces.*;

public class Date implements IDate{
    private int day;
    private int month;
    private int year;
    
    private static final int [] daysPerMonth =
    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    /**
     * Creates a new date object.
     * @param day - the day of the date.
     * @param month - the month of the date.
     * @param year - the year of the date.
     */
    public Date(int day, int month, int year){
        setYear(year);
        setMonth(month);
        setDay(day);
    }
    
    /**
     * Sets the year of the date object. Year must be an integer between 1900 and 2100.
     * @param year - the year to be set for the date.
     */
    @Override
    public void setYear(int year){
        if((year>1900) && (year<2100))
            this.year=year;
        else
            throw new IllegalArgumentException("Year must be 1900-2100");
    }
    
    /**
     * Gets the year of the date object.
     * @return The year of the date.
     */
    @Override
    public int getYear(){
        return year;
    }
    
    /**
     * Sets the month of the date object. Month must be an integer between 1-12.
     * @param month - the month to be set for the date.
     */
    @Override
    public void setMonth(int month){
        if ((month>0)&&(month<=12))
            this.month = month;
        else
            throw new IllegalArgumentException("month must be 1-12");
    }
    
    /**
     * Gets the month of the date object.
     * @return The month of the date.
     */
    @Override
    public int getMonth(){
        return month;
    }
    
    /**
     * Sets the day of the date object. The day must be a valid day that doesn't
     *  exceed the day per month limits (includes leap years)
     * @param day - the day to be set to the date object.
     */
    @Override
    public void setDay(int day){
        //It initially checks if the day fits in the months.
        if ((day>0)&&(day<=daysPerMonth[month]))
            this.day = day;
        //If not, it will check if the date is a leap year.
        else
            if ((month==2) && (day==29) && (year%400==0 || year%4==0 && year%100!=0))
                this.day = day;
        //If neither, it will throw an illegal argument exception
        else
            throw new IllegalArgumentException("Day is out of range"); 
    }
    
    /**
     * Gets the day of the date object.
     * @return The day of the date.
     */
    @Override
    public int getDay(){
        return day;
    }
    
    /**
     * Gets the date in String format.
     * @return the date object in (day/month/year) format.
     */
    @Override
    public String toString(){
        return String.format("%d/%d/%d", day, month, year);
    }

    /**
     * This method compares two dates. 
     * @param d - The date to be compared to.
     * @return An integer representing the size of the calling object compared
     *         to the specified object. Bigger if positive, equal if zero, and
     *         smaller if negative.
     */
    @Override
    public int compareTo(IDate d) {
        Date date = (Date)d;
        //Starts by comparing the year first,
        if (this.year!=date.year){
            return this.year-date.year;
        //If the same year, it will compart the month,
        }else if (this.month!=date.month){
            return this.month-date.month;
        }
        //If the same month as well, then it will compare the days.
        return this.day-date.day;
    }
}
