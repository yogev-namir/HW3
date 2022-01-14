/**
 * an interface that allows to scan a "todoList"(extends arrayList)
 */
public interface TaskIterable extends Iterable<Task>{
    void setScanningType(ScanningType type);
}
