public class TaskAlreadyExistException extends Exception{
    /**
     * Constructs a CloneNotSupportedException with no detail message.
     */
    public TaskAlreadyExistException() {
        super();
    }

    /**
     * Constructs a <code>CloneNotSupportedException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public TaskAlreadyExistException(String s) {
        super(s);
    }
}
