/** A test run without a framework: one line per check. */
public class TestArrayQueue {
    static int passed = 0, failed = 0;

    static void check(String name, boolean ok) {
        System.out.println((ok ? "PASS  " : "FAIL  ") + name);
        if (ok) passed++; else failed++;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        check("new queue is empty", q.isEmpty());
        check("new queue has size 0", q.size() == 0);

        q.enqueue(1); q.enqueue(2); q.enqueue(3);
        check("size is 3", q.size() == 3);
        check("peek is 1", q.peek() == 1);
        check("dequeue is 1", q.dequeue() == 1);
        check("dequeue is 2", q.dequeue() == 2);

        for (int i = 4; i <= 100; i++) q.enqueue(i * 10);
        check("order survives a growth", q.peek() == 3 && q.size() == 98);

        boolean order = q.dequeue() == 3;
        for (int i = 4; i <= 100; i++) order &= q.dequeue() == i * 10;
        check("FIFO order for 98 elements", order);
        check("empty again", q.isEmpty());

        try {
            q.dequeue();
            check("dequeue on empty throws", false);
        } catch (QueueUnderflowException e) {
            check("dequeue on empty throws", true);
        }

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
    }
}
