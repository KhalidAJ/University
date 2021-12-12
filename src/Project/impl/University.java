package Project.impl;

import java.util.ArrayList;
import java.util.List;
import Project.interfaces.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class University implements IUniversity{
    
    private static University instance = null;
    
    private String name;
    private List<IPerson> affiliates = new ArrayList<IPerson>();
    private List<ICourse> courses = new ArrayList<ICourse>();
    
    IPersonFactory personFactory = new PersonFactory();
    ICourseFactory courseFactory = new CourseFactory();
    
    /**
     * A method to get the instance of the university. If it doesn't exist yet, it will create one.
     * Used to follow the singleton design pattern.
     * @return the University object.
     */
    public static University getInstance(){
        //instance is initialized as null in the beginning,
        // if it is still null, then it will create an object then name it.
        if(instance == null){
            instance = new University("default");
        }
        return instance;
    }
    
    /**
     * Creates a university object.
     * @param name - The name of the university.
     */
    private University(String name){
        setName(name);
    }
    
    /**
     * Gets the number of courses offered by the university.
     * @return the number of courses.
     */
    @Override
    public int getNumberOfCourses(){
        return courses.size();
    }

    /**
     * Gets the name of the university.
     * @return the name of the university.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the university.
     * @param name - the name to be set for the university.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the list of courses offered by the university.
     * @return the list of courses.
     */
    @Override
    public List<ICourse> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses offered by the university.
     * @param courses - the list of courses.
     */
    @Override
    public void setCourses(List<ICourse> courses) {
        this.courses = courses;
    }

    /**
     * Gets the list of affiliates (students and faculty) in the university.
     * @return the list of affiliates.
     */
    @Override
    public List<IPerson> getAffiliates() {
        return this.affiliates;
    }

    /**
     * Sets the list of affiliates (students and faculty) in the university.
     * @param affiliates - list of affiliates.
     */
    @Override
    public void setAffiliates(List<IPerson> affiliates) {
        this.affiliates=affiliates;
    }
    
    /**
     * Returns the university object as a string.
     * @return the name of the university ("University: name")
     */
    @Override
    public String toString(){
            return "University: "+name;
    }

    /**
     * Gets the number of students attending the university.
     * @return the number of students attending the university.
     */
    @Override
    public int getNumberOfStudents() {
        int n = 0;
        for (IPerson affiliate : affiliates){
            if (affiliate instanceof IStudent)
                n=n+1;
        }
        return n;
    }

    /**
     * Gets the number of faculty teaching in the university.
     * @return the number of faculty teaching in the university.
     */
    @Override
    public int getNumberOfFaculty() {
        int n = 0;
        for (IPerson affiliate : affiliates){
            if (affiliate instanceof IFaculty)
                n=n+1;
        }
        return n;
    }

    /**
     * Checks whether a course is in the university or not.
     * @param c - the course to search for.
     * @return whether the course exists or not (Boolean)
     */
    @Override
    public boolean courseExist(ICourse c){
        for (ICourse course : courses){
            if (course.getId().compareTo(c.getId())==0)
                return true;
        }
        return false;
    }

    /**
     * Adds a course to the university.
     * @param course - the course to be added.
     */
    @Override
    public void addCourse(ICourse course) {
        //Checks if the course is already added, will ignore if it is
        if (!courseExist(course))
            courses.add(course);
         
    }

    /**
     * Removes a course from the university.
     * @param course - the course to be removed.
     */
    @Override
    public void removeCourse(ICourse course) {
        //Checks if the course exists, will ignore if it isn't
        if (courseExist(course))
            courses.remove(course);
    }
    
    /**
     * Searches for a course in the university.
     * @param course - the course to be searched for.
     * @return the course found.
     */
    @Override
    public ICourse findCourse(String course){
        //Iterates through the university courses,
        for(ICourse fCourse: courses){
            //then compares them to the course to be found, and returns it if found,
            if(fCourse.getId().compareTo(course) == 0)
                return fCourse;
        }
        //if it isn't found, null will be returned.
        return null;
    }

    /**
     * Loads a CSV file of students into the university object.
     * @param fileName - The name of the file containing the student data.
     */
    @Override
    public void loadStudents(String fileName) {
        try {
            //load up the file
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            //iterate through the lines of students, creating Student objects then adding them one by one
            while(sc.hasNextLine()){
                String[] line = sc.nextLine().split(",");
                String StudentID = line[0];
                String firstName = line[1];
                String lastName = line[2];
                String[] date = line[3].split("/");
                Date birthDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
                double GPA = Double.parseDouble(line[4]);
                Student student = (Student)(personFactory.getPerson("Student", StudentID, firstName, lastName, birthDate, GPA));
                if(courses.isEmpty()){
                    Scanner input = new Scanner(System.in);
                    System.out.println("No courses found, please input name of file to extract courses from.");
                    this.loadCourses(input.next());
                }
                student.getCourses().add(findCourse(line[5]));
                student.getCourses().add(findCourse(line[6]));
                if(line.length == 8){
                    student.getCourses().add(findCourse(line[7]));
                }
                affiliates.add((IPerson)student);
            }
            System.out.println("Students added successfully.");
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("File not found!");
        }
    }

    /**
     * Loads a CSV file of faculty into the university object.
     * @param fileName - The name of the file containing the faculty data.
     */
    @Override
    public void loadFaculty(String fileName) {
        try {
            //load up the file
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            //iterate through the lines of students, creating Student objects then adding them one by one
            while(sc.hasNextLine()){
                String[] line = sc.nextLine().split(",");
                String FacultyID = line[0];
                String firstName = line[1];
                String lastName = line[2];
                String[] date = line[3].split("/");
                Date birthDate = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
                double salary = Double.parseDouble(line[4]);
                Faculty faculty = (Faculty)(personFactory.getPerson("Faculty", FacultyID, firstName, lastName, birthDate, salary));
                if(courses.isEmpty()){
                    Scanner input = new Scanner(System.in);
                    System.out.println("No courses found, please input name of file to extract courses from.");
                    this.loadCourses(input.next());
                }
                faculty.getCourses().add(findCourse(line[5]));
                faculty.getCourses().add(findCourse(line[6]));
                if(line.length == 8){
                    faculty.getCourses().add(findCourse(line[7]));
                }
                affiliates.add((IPerson)faculty);
            }
            System.out.println("Faculty added successfully.");
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("File not found!");
        }
    }

    /**
     * Loads a CSV file of courses into the university object.
     * @param fileName - The name of the file containing the courses data.
     */
    @Override
    public void loadCourses(String fileName) {
        try {
            //load up the file
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            //iterate through the lines of students, creating Student objects then adding them one by one
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                String CourseID = line[0];
                String CourseTitle = line[1];
                int CreditHours = Integer.parseInt(line[2]);
                //String id, String title, int credit
                courses.add(courseFactory.createCourse(CourseID,CourseTitle,CreditHours));
            }
            System.out.println("Courses added successfully.");
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("File not found!");
        }
    }
}