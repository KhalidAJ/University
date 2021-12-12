package Project.interfaces;

public interface ICourseFactory {
    
    public abstract ICourse createCourse(String id, String title, int credit);
    
}
