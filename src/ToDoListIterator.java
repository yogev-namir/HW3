import java.util.ArrayList;
import java.util.Iterator;

public class ToDoListIterator implements Iterator<Task> {
    private final ArrayList<Task> tasksList;
    private int index;
    private static ScanningType type = ScanningType.ALL;

    /**
     * class constructor
     * @param toDoList gets a reference to the list's head
     */
    public ToDoListIterator(ToDoList toDoList){
        this.tasksList = toDoList.getTasksList();
        this.index = 0;
    }
    /**
     * determine what tasks to scan
     * @param type enum parameter
     */
    public static void setScanningType(ScanningType type) {
        ToDoListIterator.type = type;
    }
    /**
     * checks if a pre-determined-type task is found
      * @return true is there is a desired-type task, false is doesn't
     */
    @Override
    public boolean hasNext() {
        try{
            switch(type)
            {
                case UNCOMPLETED:
                    while(tasksList.get(index).isCompleted()
                            && index != tasksList.size()  )
                        index++;
                    return true;

                case COMPLETED:
                    while(!tasksList.get(index).isCompleted()
                            && index != tasksList.size() )
                        index++;
                    return true;

                default:
                    return index != tasksList.size();
            }
        }
        catch(IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * fetch the next task(by type) available
     * @return task
     */
    @Override
    public Task next() {
        if(this.hasNext())
            return tasksList.get(index++);
        else
            return null;
    }
}
