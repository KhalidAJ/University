package Project.impl;

import Project.interfaces.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UniversityTest {

    public static void main(String[] args){
        //initializing all variables
        boolean exit = false;
        boolean check1 = true;
        boolean setup = false;
        boolean check;
        String id;
        String firstName;
        String lastName;
        int d;
        int m;
        int y;
        double gpa;
        double salary;
        String title;
        int credit;
        Scanner s = new Scanner(System.in);
        
        //starts the program loop, this will create the university object and keep
        // the menu looping to allow the use of the functionality.
        while(check1){
            System.out.println("Please enter a number.");
            System.out.println("1) Start university setup.");
            System.out.println("0) Exit.");
            switch(s.next()){
                case("1"):
                    setup = true;
                    check1 = false;
                    break;
                case("0"):
                    check1 = false;
                    exit = true;
                    System.out.println("Have a good day!");
                    break;
                default:
                    System.out.println("Input unrecognizable.");
                    System.out.println("Enter anything to proceed...");
                    s.next();
            }
        }
        
        //After the initial setup, the main menu will keep looping until the
        // user chooses the option that exits the menu and ends the program.
        if(setup && !exit){
            
            System.out.println("Please enter the name of the university.");
            University uni = University.getInstance();
            uni.setName(s.next());
        
            uni.loadCourses("courses.csv");
            uni.loadFaculty("faculty.csv");
            uni.loadStudents("students.csv");
        
            UniversityAnalytics UniAnalytics = new UniversityAnalytics(uni);
            System.out.println("Enter anything to proceed...");
            s.next();
            
            while(!exit){
                System.out.println("Please enter a number.");
                System.out.println("0) Show list of students.");
                System.out.println("1) Show list of faculty.");
                System.out.println("2) Show list of courses.");
                System.out.println("3) Add item.");
                System.out.println("4) Number of students in a certain course.");
                System.out.println("5) The list of courses for each student.");
                System.out.println("6) The list of instructors for each student.");
                System.out.println("7) The GPA average of all students in a course.");
                System.out.println("8) Get the best student from each course.");
                System.out.println("9) Get the number of students in each course.");
                System.out.println("-1) Exit.");
                switch(s.next()){
                    case("0"):
                        System.out.println("Student ID   Full Name       GPA  List Of Courses");
                        for(IPerson person : uni.getAffiliates()){
                            if(person instanceof IStudent){
                                IStudent stu = (IStudent)person;
                                System.out.printf("%s:   %-15s %-4.2f %s\n",stu.getId(),(stu.getFirstName()+ " " +stu.getLastName()),stu.getGpa(),stu.getCourses());
                            }
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("1"):
                        System.out.println("Faculty ID   Full Name      Salary   List Of Courses");
                        for(IPerson person : uni.getAffiliates()){
                            if(person instanceof IFaculty){
                                IFaculty fac = (IFaculty)person;
                                System.out.printf("%s:   %-15s%-6.0f   %s\n",fac.getId(),(fac.getFirstName()+ " " +fac.getLastName()),fac.getSalary(),fac.getCourses());
                            }
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("2"):
                        System.out.println("Course ID  Course Title \t\t\t   Credit hours");
                        for(ICourse course : uni.getCourses()){
                            System.out.printf("%-9s: %-45s %d\n", course.getId(), course.getTitle(), course.getCredit());
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("3"):
                        System.out.println("Enter a type (Student, Faculty, Course).");
                        switch(s.next().toLowerCase()){
                            case("student"):
                                System.out.println("Enter the Student ID.");
                                id = s.next();
                                check = true;
                                while(check){
                                    if(!(id.substring(0, 1).matches("S") && id.substring(1).matches("[0-9]+") && id.length() == 9)){
                                        System.out.println("The ID needs to start with an \"S\" followed by 8 numbers, please try again.");
                                        id = s.next();
                                    }
                                    if(id.substring(0, 1).matches("S") && id.substring(1).matches("[0-9]+") && id.length() == 9){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the first name.");
                                firstName = s.next();
                                check = true;
                                while(check){
                                    if(!(firstName.matches("[a-zA-Z]+"))){
                                        System.out.println("The first name must only contain letters, please try again.");
                                        firstName = s.next();
                                    }
                                    if(firstName.matches("[a-zA-Z]+")){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the last name.");
                                lastName = s.next();
                                check = true;
                                while(check){
                                    if(!(lastName.matches("[a-zA-Z]+"))){
                                        System.out.println("The last name must only contain letters, please try again.");
                                        lastName = s.next();
                                    }
                                    if(lastName.matches("[a-zA-Z]+")){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the day of birth.");
                                d = s.nextInt();
                                check = true;
                                while(check){
                                    if(!(d >= 1 && d <= 31)){
                                        System.out.println("The day must be from 1 to 31, please try again.");
                                        d = s.nextInt();
                                    }
                                    if(d >= 1 && d <= 31){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the month of birth.");
                                m = s.nextInt();
                                check = true;
                                while(check){
                                    if(!(m >= 1 && m <= 12)){
                                        System.out.println("The month must be from 1 to 12, please try again.");
                                        m = s.nextInt();
                                    }
                                    if(m >= 1 && m <= 12){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the year of birth.");
                                y = s.nextInt();
                                check = true;
                                while(check){
                                    if(!(y > 1900 && y < 2100)){
                                        System.out.println("The year must be between 1900 and 2100, please try again.");
                                        y = s.nextInt();
                                    }
                                    if(y > 1900 && y < 2100){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the student's GPA.");
                                gpa = s.nextDouble();
                                check = true;
                                while(check){
                                    if(!(gpa > 0)){
                                        System.out.println("The GPA must be positive, please try again.");
                                        gpa = s.nextDouble();
                                    }
                                    if(gpa > 0){
                                        check = false;
                                    }
                                }
                                IStudent stu = new Student(id, firstName, lastName, new Date(d, m, y), gpa);
                                uni.getAffiliates().add((IPerson)(stu));
                                System.out.println("Student created.");
                                System.out.println("Please enter a number.");
                                System.out.println("1)  Add courses.");
                                System.out.println("-1) Back to main menu.");
                                switch(s.next()){
                                    case("1"):
                                        for(int i = 0; i < 3; i++){
                                            System.out.println("Enter the Course ID to add.");
                                            id = s.next();
                                            check = true;
                                            while(check){
                                                for(ICourse course : uni.getCourses()){
                                                    if(course.getId().equals(id)){
                                                        stu.getCourses().add(course);
                                                        check = false;
                                                    }
                                                }
                                                if(check){
                                                    System.out.println("ID not found, try again please.");
                                                    id = s.next();
                                                }
                                            }
                                            System.out.println("Course Added.");
                                        }
                                    case("-1"):
                                        break;
                                    default:
                                        System.out.println("Input unrecognizable, exiting to menu...");
                                        break;
                                }
                                
                                break;
                            case("faculty"):
                                System.out.println("Enter the Faculty ID.");
                                id = s.next();
                                check = true;
                                while(check){
                                    if(!(id.substring(0, 1).matches("F") && id.substring(1).matches("[0-9]+") && id.length() == 9)){
                                        System.out.println("The ID needs to start with an \"F\" followed by 8 numbers, please try again.");
                                        id = s.next();
                                    }
                                    if(id.substring(0, 1).matches("F") && id.substring(1).matches("[0-9]+") && id.length() == 9){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the first name.");
                                firstName = s.next();
                                check = true;
                                while(check){
                                    if(!(firstName.matches("[a-zA-Z]+"))){
                                        System.out.println("The first name must only contain letters, please try again.");
                                        firstName = s.next();
                                    }
                                    if(firstName.matches("[a-zA-Z]+")){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the last name.");
                                lastName = s.next();
                                check = true;
                                while(check){
                                    if(!(lastName.matches("[a-zA-Z]+"))){
                                        System.out.println("The last name must only contain letters, please try again.");
                                        lastName = s.next();
                                    }
                                    if(lastName.matches("[a-zA-Z]+")){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the day of birth.");
                                d = s.nextInt();
                                check = true;
                                while(check){
                                    if(!(d >= 1 && d <= 31)){
                                        System.out.println("The day must be from 1 to 31, please try again.");
                                        d = s.nextInt();
                                    }
                                    if(d >= 1 && d <= 31){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the month of birth.");
                                m = s.nextInt();
                                check = true;
                                while(check){
                                    if(!(m >= 1 && m <= 12)){
                                        System.out.println("The month must be from 1 to 12, please try again.");
                                        m = s.nextInt();
                                    }
                                    if(m >= 1 && m <= 12){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the year of birth.");
                                y = s.nextInt();
                                check = true;
                                while(check){
                                    if(!(y > 1900 && y < 2100)){
                                        System.out.println("The year must be between 1900 and 2100, please try again.");
                                        y = s.nextInt();
                                    }
                                    if(y > 1900 && y < 2100){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the faculty's salary.");
                                salary = s.nextDouble();
                                check = true;
                                while(check){
                                    if(!(salary > 0)){
                                        System.out.println("The salary must be positive, please try again.");
                                        salary = s.nextDouble();
                                    }
                                    if(salary > 0){
                                        check = false;
                                    }
                                }
                                IFaculty fac = new Faculty(id, firstName, lastName, new Date(d, m, y), salary);
                                uni.getAffiliates().add((IPerson)(fac));
                                System.out.println("Faculty created.");
                                System.out.println("Please enter a number.");
                                System.out.println("1)  Add courses.");
                                System.out.println("-1) Back to main menu.");
                                switch(s.next()){
                                    case("1"):
                                        for(int i = 0; i < 3; i++){
                                            System.out.println("Enter the Course ID to add.");
                                            id = s.next();
                                            check = true;
                                            while(check){
                                                for(ICourse course : uni.getCourses()){
                                                    if(course.getId().equals(id)){
                                                        fac.getCourses().add(course);
                                                        check = false;
                                                    }
                                                }
                                                if(check){
                                                    System.out.println("ID not found, try again please.");
                                                    id = s.next();
                                                }
                                            }
                                            System.out.println("Course Added.");
                                        }
                                        break;
                                    case("-1"):
                                        break;
                                    default:
                                        System.out.println("Input unrecognizable, exiting to menu...");
                                        break;
                                }
                                break;
                            case("course"):
                                System.out.println("Enter the course ID.");
                                id = s.next();
                                check = true;
                                while(check){
                                    if(!(id.matches("[A-Z0-9]+"))){
                                        System.out.println("The course id must only contain capital letters and numbers, please try again.");
                                        id = s.next();
                                    }
                                    if(id.matches("[A-Z0-9]+")){
                                        check = false;
                                    }
                                }
                                System.out.println("Enter the course title. (No spaces please)");
                                title = s.next();
                                System.out.println("Enter the course credit.");
                                credit = s.nextInt();
                                check = true;
                                while(check){
                                    if(!(credit > 0)){
                                        System.out.println("The credit must be greater than 0, please try again.");
                                        credit = s.nextInt();
                                    }
                                    if(credit > 0){
                                        check = false;
                                    }
                                }
                                uni.getCourses().add((ICourse)(new Course(id, title, credit)));
                                System.out.println("Course added.");
                                break;
                            default:
                                System.out.println("input not recognized, going back to main menu.");
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("4"):
                        System.out.println("Enter the name of the course to get the number of students.");
                        id = s.next();
                        if(UniAnalytics.numberOfStudentInCourse(id) == -999){
                            System.out.println("Course not found.");
                            System.out.println("Exiting to main menu...");
                            System.out.println("Enter anything to proceed...");
                            s.next();
                            break;
                        }
                        if(UniAnalytics.numberOfStudentInCourse(id) == 1){
                            System.out.printf("%s: %d student", id, UniAnalytics.numberOfStudentInCourse(id));
                            System.out.println("Enter anything to proceed...");
                            s.next();
                            break;
                        }
                        System.out.printf("%s: %d students\n", id, UniAnalytics.numberOfStudentInCourse(id));
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("5"):
                        Map<String, List<ICourse>> map5 = UniAnalytics.StudentCourseMap();
                        System.out.println("Student ID: List of Courses");
                        for(String mapId : map5.keySet()){
                            System.out.printf("%-10s: %s\n", mapId, map5.get(mapId));
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("6"):
                        Map<String, List<String>> map6 = UniAnalytics.StudentInstructorMap();
                        System.out.println("Student ID: List of Instructor IDs");
                        for(String mapId : map6.keySet()){
                            System.out.printf("%-10s: %s\n", mapId, map6.get(mapId));
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("7"):
                        Map<String, Double> map7 = UniAnalytics.GpaAverageByCourse();
                        System.out.println("Course ID : GPA Average");
                        for(String mapId : map7.keySet()){
                            System.out.printf("%-10s: %.3f\n", mapId, map7.get(mapId));
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("8"):
                        Map<String, IStudent> map8 = UniAnalytics.BestStudentPerCourse();
                        System.out.println("Course ID : Best Student");
                        for(String mapId : map8.keySet()){
                            System.out.printf("%-10s: %s\n", mapId, map8.get(mapId));
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("9"):
                        System.out.println("Enter the minimum number of students to filter.");
                        String num = s.next();
                        if(!num.matches("[0-9]+")){
                            System.out.println("Input unrecognizable, exiting to main menu...");
                            System.out.println("Enter anything to proceed...");
                            s.next();
                            break;
                        }
                        Map<String, Integer> map9 = UniAnalytics.NumberOfStudentsInCourses(Integer.parseInt(num));
                        System.out.println("Course ID : Num Of Students");
                        for(String mapId : map9.keySet()){
                            System.out.printf("%-10s:      %d\n", mapId, map9.get(mapId));
                        }
                        System.out.println("Enter anything to proceed...");
                        s.next();
                        break;
                    case("-1"):
                        System.out.println("Have a good day!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Input unrecognizable.");
                        System.out.println("Enter anything to proceed...");
                        s.next();
                }
            }
        }
    }
}