import java.util.concurrent.atomic.AtomicReference;

/** Michael & Scott (1996): a lock-free FIFO queue with a dummy node. */
public class MsQueue<T> {
    private static class Node<T> {
        final T item;
        final AtomicReference<Node<T>> next = new AtomicReference<>();
        Node(T item) { this.item = item; }
    }

    private final AtomicReference<Node<T>> head;
    private final AtomicReference<Node<T>> tail;

    public MsQueue() {
        Node<T> dummy = new Node<>(null);
        head = new AtomicReference<>(dummy);
        tail = new AtomicReference<>(dummy);
    }

    public void enqueue(T x) {
        Node<T> n = new Node<>(x);
        while (true) {
            Node<T> last = tail.get();
            Node<T> next = last.next.get();
            if (last != tail.get()) continue;               // tail moved: retry
            if (next == null) {
                if (last.next.compareAndSet(null, n)) {
                    tail.compareAndSet(last, n);            // if it fails, a helper does it
                    return;
                }
            } else {
                tail.compareAndSet(last, next);             // help the slow thread
            }
        }
    }

    public T dequeue() {
        while (true) {
            Node<T> first = head.get();
            Node<T> last = tail.get();
            Node<T> next = first.next.get();
            if (first != head.get()) continue;              // head moved: retry
            if (first == last) {
                if (next == null) return null;              // empty
                tail.compareAndSet(last, next);
            } else {
                T x = next.item;
                if (head.compareAndSet(first, next)) return x;
            }
        }
    }
}
