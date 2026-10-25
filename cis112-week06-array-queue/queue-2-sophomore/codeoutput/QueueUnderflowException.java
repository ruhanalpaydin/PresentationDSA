/** Thrown when dequeue or peek is called on an empty queue. */
public class QueueUnderflowException extends RuntimeException {
    public QueueUnderflowException(String operation) {
        super("underflow: cannot " + operation + " an empty queue");
    }
}
