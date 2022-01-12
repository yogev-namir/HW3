public class TaskAlreadyExistsException extends Exception{
    /**
     * Constructs a CloneNotSupportedException with no detail message.
     */
    public TaskAlreadyExistsException() {
        super();
    }

    /**
     * Constructs a <code>CloneNotSupportedException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public TaskAlreadyExistsException(String s) {
        super(s);
    }
}
