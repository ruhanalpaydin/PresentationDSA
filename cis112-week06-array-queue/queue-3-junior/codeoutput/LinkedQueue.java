import java.util.Iterator;
import java.util.NoSuchElementException;

/** A queue as a singly linked list: the front is the head, the rear is the tail. */
public class LinkedQueue<T> implements Iterable<T> {
    private static class Node<T> {
        T item;
        Node<T> next;
        Node(T item, Node<T> next) { this.item = item; this.next = next; }
    }

    private Node<T> head;      // front, null when empty
    private Node<T> tail;      // rear
    private int size;

    public void enqueue(T x) {
        Node<T> n = new Node<>(x, null);
        if (tail == null) head = n; else tail.next = n;
        tail = n;
        size++;
    }

    public T dequeue() {
        if (head == null) throw new IllegalStateException("underflow: queue is empty");
        T x = head.item;
        head = head.next;
        if (head == null) tail = null;   // the queue just became empty
        size--;
        return x;
    }

    public T peek() {
        if (head == null) throw new IllegalStateException("underflow: queue is empty");
        return head.item;
    }

    public boolean isEmpty() { return head == null; }
    public int size()        { return size; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> p = head;
            @Override public boolean hasNext() { return p != null; }
            @Override public T next() {
                if (p == null) throw new NoSuchElementException();
                T x = p.item;
                p = p.next;
                return x;
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
