/** A bounded ring buffer: when it is full, the oldest element is overwritten. */
public class RingBuffer<T> {
    private final Object[] a;
    private int head = 0, tail = 0, size = 0;
    private long dropped = 0;

    public RingBuffer(int capacity) { a = new Object[capacity]; }

    public void put(T x) {
        if (size == a.length) {          // full: drop the oldest
            a[head] = null;
            head = (head + 1) % a.length;
            size--;
            dropped++;
        }
        a[tail] = x;
        tail = (tail + 1) % a.length;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T take() {
        if (size == 0) return null;
        T x = (T) a[head];
        a[head] = null;
        head = (head + 1) % a.length;
        size--;
        return x;
    }

    public int size()      { return size; }
    public long dropped()  { return dropped; }
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) sb.append(i > 0 ? ", " : "").append(a[(head + i) % a.length]);
        return sb.append("]").toString();
    }
}
