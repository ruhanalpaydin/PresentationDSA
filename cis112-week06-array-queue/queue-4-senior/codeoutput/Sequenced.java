import java.util.ArrayDeque;
import java.util.List;

/** Java 21 SequencedCollection: getFirst, getLast, reversed. */
public class Sequenced {
    public static void main(String[] args) {
        ArrayDeque<String> q = new ArrayDeque<>(List.of("A", "B", "C", "D"));
        System.out.println("queue      = " + q);
        System.out.println("getFirst() = " + q.getFirst());
        System.out.println("getLast()  = " + q.getLast());
        System.out.println("reversed() = " + q.reversed());
        q.addFirst("Z");
        q.addLast("E");
        System.out.println("after add  = " + q);
        System.out.println("removeFirst = " + q.removeFirst());
        System.out.println("removeLast  = " + q.removeLast());
        System.out.println("left        = " + q);
    }
}
