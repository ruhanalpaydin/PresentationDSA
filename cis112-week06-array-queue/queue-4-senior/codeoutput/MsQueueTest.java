/** Four threads enqueue at the same time; one thread drains and checks. */
public class MsQueueTest {
    static final int THREADS = 4, PER_THREAD = 100_000;

    public static void main(String[] args) throws Exception {
        MsQueue<Integer> q = new MsQueue<>();
        Thread[] ts = new Thread[THREADS];
        long t0 = System.nanoTime();
        for (int t = 0; t < THREADS; t++) {
            final int id = t;
            ts[t] = new Thread(() -> {
                for (int i = 0; i < PER_THREAD; i++) q.enqueue(id * PER_THREAD + i);
            });
            ts[t].start();
        }
        for (Thread th : ts) th.join();
        long t1 = System.nanoTime();

        long count = 0, sum = 0;
        Integer x;
        while ((x = q.dequeue()) != null) { count++; sum += x; }

        long expectedCount = (long) THREADS * PER_THREAD;
        long expectedSum = expectedCount * (expectedCount - 1) / 2;
        System.out.println("enqueued by " + THREADS + " threads = " + expectedCount);
        System.out.println("dequeued                    = " + count);
        System.out.println("checksum ok                 = " + (sum == expectedSum));
        System.out.println("time                        = " + (t1 - t0) / 1_000_000 + " ms");
    }
}
