import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** One producer, one consumer, a queue of capacity 4. */
public class ProducerConsumer {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(4);
        int items = 1000;
        long[] sum = new long[1];

        Thread producer = new Thread(() -> {
            try { for (int i = 0; i < items; i++) q.put(i); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "producer");

        Thread consumer = new Thread(() -> {
            try { for (int i = 0; i < items; i++) sum[0] += q.take(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "consumer");

        long t0 = System.nanoTime();
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        long t1 = System.nanoTime();

        System.out.println("items      = " + items);
        System.out.println("sum        = " + sum[0] + "  (expected " + (items * (items - 1) / 2) + ")");
        System.out.println("queue left = " + q.size());
        System.out.println("elapsed    = " + (t1 - t0) / 1_000_000 + " ms");
    }
}
