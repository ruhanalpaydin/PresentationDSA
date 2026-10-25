public class Trace {
    public static void main(String[] args) {
        IntQueue q = new IntQueue(4);

        q.enqueue(5);
        System.out.printf("%-13s %s%n", "enqueue(5)", q.debug());
        q.enqueue(8);
        System.out.printf("%-13s %s%n", "enqueue(8)", q.debug());
        System.out.printf("%-13s %d   %s%n", "dequeue() =", q.dequeue(), q);
        q.enqueue(2);
        System.out.printf("%-13s %s%n", "enqueue(2)", q.debug());
        q.enqueue(7);
        System.out.printf("%-13s %s%n", "enqueue(7)", q.debug());
        System.out.printf("%-13s %d   %s%n", "peek() =", q.peek(), q);
        System.out.printf("%-13s %d%n", "size() =", q.size());
    }
}
