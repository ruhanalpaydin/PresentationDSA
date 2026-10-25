import java.util.Iterator;
import java.util.NoSuchElementException;

/** A queue in a circular array that grows by doubling and shrinks at one quarter. */
public class ArrayQueue<T> implements QueueADT<T> {
    private T[] a;
    private int head = 0;     // index of the front element
    private int tail = 0;     // index where the next element goes
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayQueue() { a = (T[]) new Object[4]; }

    @Override
    public void enqueue(T x) {
        if (size == a.length) resize(2 * a.length);
        a[tail] = x;
        tail = (tail + 1) % a.length;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new QueueUnderflowException("dequeue");
        T x = a[head];
        a[head] = null;                       // do not keep a reference to a dequeued element
        head = (head + 1) % a.length;
        size--;
        if (a.length > 4 && size == a.length / 4) resize(a.length / 2);
        return x;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new QueueUnderflowException("peek");
        return a[head];
    }

    @Override public boolean isEmpty() { return size == 0; }
    @Override public int size()        { return size; }
    public int capacity()              { return a.length; }

    /** Copy the elements in queue order to a new array, starting at index 0. */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] b = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) b[i] = a[(head + i) % a.length];
        a = b;
        head = 0;
        tail = size;
    }

    @Override
    public Iterator<T> iterator() {          // front to rear
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
        for (T x : this) sb.append(sb.length() > 7 ? ", " : "").append(x);
        return sb.append("]").toString();
    }
}
