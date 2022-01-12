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
       return new ToDoListIterator(this);

    }
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
    public void addTask(Task task) throws TaskAlreadyExistsException{
        for(Task t : tasksList) {
                if (t.getDescription().equals(task.getDescription()))
                    throw new TaskAlreadyExistsException();
                //Task temp = t.clone();

            }
        tasksList.add(task);

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

    @Override
    public ToDoList clone() {
        try {

            ToDoList temp = (ToDoList) super.clone();
            ToDoListIterator it = new ToDoListIterator(this);
            ToDoList clonedList=new ToDoList();

          //  ToDoListIterator.setScanningType(ScanningType.ALL);
            while(it.hasNext()){
                clonedList.addTask(( it.next()).clone());
                //clonedList.addTask(newTask);
            }
            return clonedList;
        } catch (CloneNotSupportedException | TaskAlreadyExistsException e) {
            return null;
        }
    }
    @Override
        public boolean equals(Object tdl) {
            if (this == tdl) return true;
            if (tdl == null || this.getClass() != tdl.getClass()) return false;
            ToDoList tasks = (ToDoList) tdl;
            ToDoList compare1=this.createUncompletedList();
            ToDoList compare2=tasks.createUncompletedList();
            //compare1.createUncompletedList();
           if ( compare1.tasksList.containsAll(compare2.tasksList) &&
                   compare2.tasksList.containsAll(compare1.tasksList)){
                return true;
            }
            return false;
        }

        }

