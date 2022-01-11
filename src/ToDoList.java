import java.util.*;


public class ToDoList implements Iterable<Task>, Cloneable, TaskIterable{
    ArrayList<Task> tasksList;

    /**
     * constructor
     */
    public ToDoList() {
        this.tasksList = new ArrayList<>();
    }

    @Override
    public Iterator<Task> iterator() {
        ToDoListIterator it = new ToDoListIterator(this);
        return tasksList.iterator();
    }
    public ToDoList createUncompletedList(){
        ToDoList cloned = this.clone();
        for(Task t : cloned.tasksList){
            if(t.taskStatus())
               cloned.tasksList.remove(t);
        }
        return cloned;
    }
    public void addTask(Task task){
        for(Task t : tasksList) {
            try {
                if (t.getDescription().equals(task.getDescription()))
                    throw new TaskAlreadyExistException();
                Task temp = t.clone();
                tasksList.add(temp);
            }
            catch (TaskAlreadyExistException e) {
                return;
            }
        }
    }
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }
    /*public void setTasksList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }
     */
    public void setScanningType(ScanningType type){
        ToDoListIterator.setScanningType(type);
    }
    @Override
    public String toString() {
        Iterator<Task> it = iterator();
        if (! it.hasNext())
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[').append('(');
        for (;;) {
            Task task = it.next();
            sb.append('(').append(task).append(')');
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    @Override
    public ToDoList clone() {
        try {
            ToDoList clonedList = (ToDoList) super.clone();
            Iterator<Task> it = this.iterator();
            while(it.hasNext()){
                Task originalTask = it.next();
                Task newTask= originalTask.clone();
                clonedList.addTask(newTask);
            }
            return clonedList;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoList tasks = (ToDoList) o;
        return Objects.equals(tasksList, tasks.tasksList);
    }
}
