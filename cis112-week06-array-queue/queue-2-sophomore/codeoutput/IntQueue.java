/** A queue of int values in a fixed-size circular array. */
public class IntQueue {
    private final int[] a;    // the slots of the circular array
    private int head = 0;     // index of the front element
    private int tail = 0;     // index where the next element goes
    private int size = 0;     // how many elements are in the queue

    public IntQueue(int capacity) {
        a = new int[capacity];
    }

    public void enqueue(int x) {
        if (size == a.length) throw new IllegalStateException("overflow: queue is full");
        a[tail] = x;
        tail = (tail + 1) % a.length;   // wrap around
        size++;
    }

    public int dequeue() {
        if (size == 0) throw new IllegalStateException("underflow: queue is empty");
        int x = a[head];
        head = (head + 1) % a.length;   // wrap around
        size--;
        return x;
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException("underflow: queue is empty");
        return a[head];
    }

    public boolean isEmpty() { return size == 0; }
    public int size()        { return size; }

    /** For tracing on paper: the elements and the two indices. */
    public String debug() { return toString() + "   head=" + head + ", tail=" + tail; }

    @Override
    public String toString() {                // front ... rear
        StringBuilder sb = new StringBuilder("front [");
        for (int i = 0; i < size; i++) sb.append(i > 0 ? ", " : "").append(a[(head + i) % a.length]);
        return sb.append("]").toString();
    }
}
