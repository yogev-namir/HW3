import java.util.*;


public class Task implements Cloneable{
    private String description = "";
    private Date dueDate;
    private Boolean isDone = false;

    public Task(String name, Date date){
        setDescription(name);
        setDueDate(date);
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void setAsComplete(){
        isDone = true;
    }
    public String getDescription() {
        return description;
    }
    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return getDescription()+","+getDueDate();
    }

    public boolean taskStatus(){
        return isDone;
    }

    @Override
    public boolean equals(Object taskTwo) {
        if (this == taskTwo) return true;
        if (taskTwo == null || getClass() != taskTwo.getClass()) return false;
        Task task = (Task) taskTwo;
        return Objects.equals(description, task.description) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(isDone, task.isDone);
    }

    @Override
    public Task clone() {
        try {
            Task clone = (Task) super.clone();
            clone.dueDate = (Date) dueDate.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
