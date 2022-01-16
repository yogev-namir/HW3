

import java.util.*;

public class ToDoList implements Iterable<Task>, Cloneable, TaskIterable{
    private ArrayList<Task> tasksList;

    /**
     * class constructor, creates new ArrayList
     */
    public ToDoList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     *
     * @return creates new Iterator for the list
     */
    @Override
    public Iterator<Task> iterator() {
       return new ToDoListIterator(this);

    }

    /**
     * @return only the uncomplited task in the list
     */
    public ToDoList createUncompletedList() {
        ToDoList cloned = this.clone();
        if (cloned != null){
            for (Task t : this.tasksList) {
                if (t.isCompleted())
                    cloned.tasksList.remove(t);
            }
        }
        return cloned;
    }

    /**
     * adds a task to the list, if similar one does not already exist
     * @param task the task we want to append to the list
     * @throws TaskAlreadyExistsException
     */
    public void addTask(Task task) throws TaskAlreadyExistsException{
        for(Task t : tasksList)
                if (t.getDescription().equals(task.getDescription()))
                    throw new TaskAlreadyExistsException();
        getTasksList().add(task);
    }

    /**
     * @return an ArrayList<Task>
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * @param type enum parameter, determine which tasks will be iterated
     */
    public void setScanningType(ScanningType type){
        ToDoListIterator.setScanningType(type);
    }

    /**
     * @return all the uncompleted tasks in the list, description and due date
     */
    @Override
    public String toString() {
        ToDoListIterator it = new ToDoListIterator(this);
        ToDoListIterator.setScanningType(ScanningType.UNCOMPLETED);
        if (!it.hasNext())
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            Task task = it.next();
            sb.append('(').append(task).append(')');
            if (! it.hasNext()) {
                ToDoListIterator.setScanningType(ScanningType.ALL);
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }

    /**
     * @return creates a deep-copy clone of the given tasks list(all tasks)
     */
    @Override
    public  ToDoList clone(){
        try{
            ToDoList listClone = (ToDoList) super.clone();
            listClone.tasksList = (ArrayList<Task>) this.getTasksList().clone();
            for(int i = 0; i < listClone.tasksList.size(); i++) {
                listClone.tasksList.set(i,listClone.getTask(i).clone());
            }
            return listClone;
        }
        catch(CloneNotSupportedException | NullPointerException e){
            return null;
        }
    }

    /**
     * @param i task's index in the list
     * @return the desired task if excise
     */
    private Task getTask(int i) {
        try{
            return this.tasksList.get(i);
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * @param tdl is the other to do list we are comparing with
     * @return true if it has the same uncompleted tasks, false if not
     */
    @Override
        public boolean equals(Object tdl) {
            if (this == tdl) return true;
            if (tdl == null || this.getClass() != tdl.getClass()) return false;
            ToDoList tasks = (ToDoList) tdl;
            ToDoList compare1=this.createUncompletedList();
            ToDoList compare2=tasks.createUncompletedList();

            return compare1.tasksList.containsAll(compare2.tasksList) &&
                compare2.tasksList.containsAll(compare1.tasksList);
    }
}

