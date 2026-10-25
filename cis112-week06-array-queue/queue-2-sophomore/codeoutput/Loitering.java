import java.lang.ref.WeakReference;

/** A dequeued element must become garbage: dequeue() clears a[head]. */
public class Loitering {
    public static void main(String[] args) throws Exception {
        ArrayQueue<byte[]> q = new ArrayQueue<>();
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
