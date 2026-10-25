import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** A single worker takes tasks from its queue in the order they were submitted. */
public class PoolDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++) {
            int id = i;
            pool.execute(() -> System.out.println("task " + id + " on " + Thread.currentThread().getName()));
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("pool closed = " + pool.isShutdown());
    }
}
