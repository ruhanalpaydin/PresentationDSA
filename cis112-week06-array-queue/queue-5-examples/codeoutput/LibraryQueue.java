import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LibraryQueue {
    public static void main(String[] args) {
        Queue<String> q = new ArrayDeque<>();
        q.offer("Ali");
        q.offer("Ayse");
        q.offer("Mehmet");
        System.out.println("queue       = " + q);          // front is on the left
        System.out.println("poll()      = " + q.poll());
        System.out.println("peek()      = " + q.peek());
        System.out.println("queue       = " + q);
        System.out.println("poll()      = " + q.poll());
        System.out.println("poll()      = " + q.poll());
        System.out.println("poll(empty) = " + q.poll());  // null, no exception
        try {
            q.element();
        } catch (NoSuchElementException e) {
            System.out.println("element(empty) throws " + e);
        }
    }
}
