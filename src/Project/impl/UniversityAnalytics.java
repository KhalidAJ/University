package Project.impl;

import Project.interfaces.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UniversityAnalytics implements IUniversityAnalytics {

    private University uni;

    /**
     * Creates a university analytics object
     * @param uni - The university object to create the analytics for.
     */
    public UniversityAnalytics(University uni) {
        this.uni = uni;
    }

    /**
     * Creates a list of all courses and their students.
     * @return a map with the courses as the key and the students as the values.
     */
    @Override
    public Map<ICourse, List<IStudent>> courseStudentList() {
        Map<ICourse, List<IStudent>> map = new TreeMap<ICourse, List<IStudent>>();
        for (ICourse c : uni.getCourses()) {
            List<IStudent> students = new ArrayList<IStudent>();
            for (IPerson person : uni.getAffiliates()) {
                if (person instanceof Student) {
                    Student student = (Student) person;
                    if (student.getCourses().contains(c)) {
                        students.add(student);
                    }
                }
            }
            map.put((Course)c, students);
        }
        return map;
    }

    /**
     * Check the number of students attending a course.
     * @param CourseID - the course ID.
     * @return the number of students in the course.
     */
    @Override
    public int numberOfStudentInCourse(String CourseID) {
        Map<ICourse, List<IStudent>> list = this.courseStudentList();
        for (ICourse c : list.keySet()) {
            if (c.getId().compareTo(CourseID) == 0) {
                return list.get(c).size();
            }
        }
        return -1;
    }

    /**
     * Creates a list of all students and their courses.
     * @return a map with the students as the key and their courses as the values.
     */
    @Override
    public Map<String, List<ICourse>> StudentCourseMap() {
        Map<String, List<ICourse>> map = new TreeMap<String, List<ICourse>>();
        for (IPerson person : uni.getAffiliates()) {
            if (person instanceof IStudent) {
                Student student = (Student)person;
                String studentId = student.getId();
                List<ICourse> courses = new ArrayList<ICourse>();
                for(ICourse iCourse : student.getCourses()){
                    Course course = (Course)iCourse;
                    courses.add(course);
                }
                map.put(studentId, courses);
            }
        }
        return map;
    }

    /**
     * Creates a list of the students and their instructors.
     * @return a map with the student IDs as the keys and the course IDs as the values.
     */
    @Override
    public Map<String, List<String>> StudentInstructorMap() {
        Map<String, List<String>> map = new TreeMap<String, List<String>>();
        ArrayList<IStudent> students = new ArrayList<IStudent>();
        Comparator<IStudent> comparator = new FirstNameComparator();
        Map<ICourse, IFaculty> courseInstructor = new TreeMap<ICourse, IFaculty>();
        for (ICourse course : uni.getCourses()) {
            for (IPerson person : uni.getAffiliates()) {
                if (person instanceof IFaculty) {
                    Faculty faculty = (Faculty) person;
                    if (faculty.getCourses().contains(course)) {
                        courseInstructor.put(course, faculty);
                    }
                }
            }
        }
        for (IPerson person : uni.getAffiliates()) {
            if (person instanceof IStudent) {
                students.add((Student) person);
            }
        }
        Collections.sort(students, comparator);
        for (IStudent student : students) {
            List<String> StudentIDs = new ArrayList<String>();
            for (ICourse c : ((Student) student).getCourses()) {
                StudentIDs.add(courseInstructor.get(c).getId());
            }
            map.put(student.getId(), StudentIDs);
        }
        Map<String, List<String>> finalMap = new TreeMap<String, List<String>>();
        for(IStudent student : students){
            finalMap.put(student.getId(), map.get(student.getId()));
        }
        return finalMap;
    }

    /**
     * Creates a list with each course and it's average GPA.
     * @return - A map with the course ID as the key and the GPA as the value
     */
    @Override
    public Map<String, Double> GpaAverageByCourse() {
        Map<String, Double> map = new TreeMap<String, Double>();
        Map<ICourse, List<IStudent>> list = this.courseStudentList();
        for (ICourse course : list.keySet()){
            String courseID = course.getId();
            double sum = 0;
            for (IStudent student : list.get(course)) {
                sum += ((Student) student).getGpa();
            }
            double avg = sum / (list.get(course).size());
            map.put(courseID, avg);
        }
        return map;
    }

    /**
     * Calculates and returns the best performing student per course.
     * @return A map with the course ID as the key and the Student as the value.
     */
    @Override
    public Map<String, IStudent> BestStudentPerCourse() {
        Map<String, IStudent> map = new TreeMap<String, IStudent>();
        Map<ICourse, List<IStudent>> list = this.courseStudentList();
        for (ICourse course : list.keySet()) {
            String courseID = course.getId();
            double highestGpa = 0;
            IStudent bestStudent = null;
            for (IStudent student : list.get(course)) {
                double gpa = ((Student) student).getGpa();
                if (gpa > highestGpa) {
                    highestGpa = gpa;
                    bestStudent = student;
                }
                map.put(courseID, bestStudent);
            }
        }
        return map;
    }

    /**
     * Returns a list with the courses and the number of students attending each course. 
     * @param minNumber - Only returns the courses with more than this minimum number.
     * @return A map with the course ID as the keys and the number of students as the value.
     */
    @Override
    public Map<String, Integer> NumberOfStudentsInCourses(int minNumber) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        Map<ICourse, List<IStudent>> list = this.courseStudentList();
        for (ICourse course : list.keySet()) {
            String courseId = course.getId();
            int numOfStudents = list.get(course).size();
            if (numOfStudents > minNumber) {
                map.put(courseId, numOfStudents);
            }
        }
        return map;
    }
}
