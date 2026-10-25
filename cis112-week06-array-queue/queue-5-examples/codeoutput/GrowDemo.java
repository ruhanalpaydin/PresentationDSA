public class GrowDemo {
    public static void main(String[] args) {
        ArrayQueue<String> q = new ArrayQueue<>();
        q.enqueue("A"); q.enqueue("B");
        System.out.printf("%-4s %-24s capacity=%d size=%d%n", "", q, q.capacity(), q.size());
        q.enqueue("C"); q.enqueue("D");          // full
        System.out.printf("%-4s %-24s capacity=%d size=%d%n", "", q, q.capacity(), q.size());
        q.dequeue(); q.dequeue();                // head moves to 2
        q.enqueue("E"); q.enqueue("F");          // tail wraps
        System.out.printf("%-4s %-24s capacity=%d size=%d%n", "wrap", q, q.capacity(), q.size());
        q.enqueue("G");                          // full: grow, then unwrap
        System.out.printf("%-4s %-24s capacity=%d size=%d%n", "grow", q, q.capacity(), q.size());
        q.enqueue("H");
        System.out.printf("%-4s %-24s capacity=%d size=%d%n", "", q, q.capacity(), q.size());
    }
}
