import java.util.ArrayDeque;
import java.util.Deque;

public class DequeDemo {
    public static void main(String[] args) {
        Deque<String> d = new ArrayDeque<>();
        d.addLast("B");
        d.addFirst("A");
        d.addLast("C");
        System.out.println("deque        = " + d);
        System.out.println("pollFirst()  = " + d.pollFirst());
        System.out.println("pollLast()   = " + d.pollLast());
        System.out.println("deque        = " + d);
        d.push("front");                 // push = addFirst
        d.offerLast("rear");
        System.out.println("after push/offerLast = " + d);
        System.out.println("pop()        = " + d.pop());
    }
}
