/** The queue ADT: what a queue does, not how it does it. */
public interface QueueADT<T> extends Iterable<T> {
    void enqueue(T x);      // add at the rear
    T dequeue();            // remove and return the front
    T peek();               // the front, not removed
    boolean isEmpty();
    int size();
}
