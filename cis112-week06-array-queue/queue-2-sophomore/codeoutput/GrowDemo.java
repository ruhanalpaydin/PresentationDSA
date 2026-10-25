public class GrowDemo {
    public static void main(String[] args) {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        System.out.printf("%-14s capacity=%d size=%d%n", "new", q.capacity(), q.size());

        for (int i = 1; i <= 4; i++) q.enqueue(i);
        System.out.printf("%-14s capacity=%d %s%n", "4 enqueues", q.capacity(), q);

        q.enqueue(5);
        System.out.printf("%-14s capacity=%d %s%n", "5th enqueue", q.capacity(), q);

        for (int i = 0; i < 4; i++) q.dequeue();
        System.out.printf("%-14s capacity=%d %s%n", "4 dequeues", q.capacity(), q);
    }
}
