import java.util.ArrayList;
import java.util.Iterator;

public class ToDoListIterator implements Iterator<Task> {
    private final ArrayList<Task> tasksList;
    private int index;
    private static ScanningType type = ScanningType.ALL;

    public static void setScanningType(ScanningType type) {
        ToDoListIterator.type = type;
    }

    public ToDoListIterator(ToDoList toDoList){
        this.tasksList = toDoList.tasksList;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        return index != tasksList.size();
    }

    @Override
    public Task next() {
        if(this.hasNext())
            return tasksList.get(index++);
        else
            return null;
    }
}
