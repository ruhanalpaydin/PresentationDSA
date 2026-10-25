import java.util.Iterator;
import java.util.NoSuchElementException;

/** A queue in a circular array that doubles when it is full. */
public class ArrayQueue<T> implements Iterable<T> {
    private T[] a;
    private int head = 0;     // index of the front element
    private int tail = 0;     // index where the next element goes
    private int size = 0;     // how many elements are in the queue

    @SuppressWarnings("unchecked")
    public ArrayQueue() { a = (T[]) new Object[4]; }

    public void enqueue(T x) {
        if (size == a.length) grow();
        a[tail] = x;
        tail = (tail + 1) % a.length;
        size++;
    }

    public T dequeue() {
        if (size == 0) throw new IllegalStateException("underflow: queue is empty");
        T x = a[head];
        a[head] = null;                  // let the garbage collector take it
        head = (head + 1) % a.length;
        size--;
        return x;
    }

    public T peek() {
        if (size == 0) throw new IllegalStateException("underflow: queue is empty");
        return a[head];
    }

    public boolean isEmpty() { return size == 0; }
    public int size()        { return size; }
    public int capacity()    { return a.length; }

    @SuppressWarnings("unchecked")
    private void grow() {                // copy in queue order, starting at index 0
        T[] b = (T[]) new Object[2 * a.length];
        for (int i = 0; i < size; i++)
            b[i] = a[(head + i) % a.length];
        a = b;
        head = 0;
        tail = size;
    }

    @Override
    public Iterator<T> iterator() {      // front to rear
        return new Iterator<>() {
            private int i = 0;
            @Override public boolean hasNext() { return i < size; }
            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return a[(head + i++) % a.length];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("front [");
        for (int i = 0; i < size; i++) sb.append(i > 0 ? ", " : "").append(a[(head + i) % a.length]);
        return sb.append("]").toString();
    }
}
