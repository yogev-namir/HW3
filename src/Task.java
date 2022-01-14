
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Task implements Cloneable{
    private String description = "";
    private Date dueDate;
    private Boolean isDone = false;

    /**
     * class constructor
     * @param name task's description
     * @param date task's due Date
     */
    public Task(String name, Date date){
        setDescription(name);
        setDueDate(date);
    }

    /**
     * @param description task's name
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param dueDate submission date
     */
    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}

    /**
     * when a student finishes the task he marks it as Done(true val)
     */
    public void setAsComplete(){isDone = true;}

    /**
     * @return task's description
     */
    public String getDescription() {return description;}

    /**
     * @return task's last day of submission
     */
    public Date getDueDate() {return dueDate;}
    /**
     * used when a user trys to print a task as an object
     * @return it's name\description and due date as a String
     */
    @Override
    public String toString() {
        try{
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            return getDescription() + ", " +formatter.format(getDueDate());
        }
        catch(NullPointerException e){
            return null;
        }
    }

    /**
     * @return task's status - done(true) / in progress(false)
     */
    public boolean isCompleted() { return isDone; }

    /**
     * @param taskTwo the task we compare the first one with
     * @return true if they have the same values or the same object
     */
    @Override
    public boolean equals(Object taskTwo) {
        if (this == taskTwo) return true;
        if (taskTwo == null || getClass() != taskTwo.getClass()) return false;
        Task task = (Task) taskTwo;
        return Objects.equals(description, task.description) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(isDone, task.isDone);
    }

    /**
     * deep clone, creates new instance
     * @return cloned task
     */
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
