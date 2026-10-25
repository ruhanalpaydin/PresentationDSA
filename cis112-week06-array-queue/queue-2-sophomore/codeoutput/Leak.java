import java.lang.ref.WeakReference;

/** The same program, but dequeue() does not clear a[head]. */
public class Leak {
    static class LeakyQueue<T> {
        Object[] a = new Object[4];
        int head = 0, tail = 0, size = 0;
        void enqueue(T x) { a[tail] = x; tail = (tail + 1) % a.length; size++; }
        T dequeue() {
            @SuppressWarnings("unchecked") T x = (T) a[head];
            head = (head + 1) % a.length; size--;   // a[head] is not cleared
            return x;
        }
    }

    public static void main(String[] args) throws Exception {
        LeakyQueue<byte[]> q = new LeakyQueue<>();
        byte[] data = new byte[8_000_000];
        WeakReference<byte[]> ref = new WeakReference<>(data);
        q.enqueue(data);
        q.dequeue();
        data = null;
        System.gc();
        Thread.sleep(200);
        System.gc();
        System.out.println(ref.get() == null
                ? "after dequeue: the 8 MB array was collected"
                : "after dequeue: the 8 MB array is still reachable");
    }
}
