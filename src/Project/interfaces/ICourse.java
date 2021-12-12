package Project.interfaces;

public interface ICourse extends Comparable<ICourse>{
    
    public abstract String getId();
    public abstract String getTitle();
    public abstract int getCredit();
    public abstract void setId(String id);
    public abstract void setTitle(String title);
    public abstract void setCredit(int credit);
    
}