public class Trace {
    public static void main(String[] args) {
        IntQueue q = new IntQueue(4);

        q.enqueue(5);
        System.out.printf("%-13s %s%n", "enqueue(5)", q.debug());
        q.enqueue(8);
        System.out.printf("%-13s %s%n", "enqueue(8)", q.debug());
        q.enqueue(13);
        System.out.printf("%-13s %s%n", "enqueue(13)", q.debug());
        q.enqueue(21);
        System.out.printf("%-13s %s%n", "enqueue(21)", q.debug());
        System.out.printf("%-13s %d   %s%n", "dequeue() =", q.dequeue(), q);
        System.out.printf("%-13s %d   %s%n", "dequeue() =", q.dequeue(), q);
        q.enqueue(34);
        System.out.printf("%-13s %s%n", "enqueue(34)", q.debug());
        q.enqueue(55);
        System.out.printf("%-13s %s%n", "enqueue(55)", q.debug());
        System.out.printf("%-13s %d   (capacity 4)%n", "size() =", q.size());
    }
}
