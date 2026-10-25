import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * enqueue + dequeue with a queue that holds about 1000 elements.
 * The queues are fields, so the JIT cannot remove the objects.
 * One run on one machine: use the numbers for the shape, not as a law.
 */
public class Bench {
    static final int N = 5_000_000;
    static final int WINDOW = 1000;

    static IntQueue a = new IntQueue(2048);
    static ArrayQueue<Integer> b = new ArrayQueue<>();
    static LinkedQueue<Integer> c = new LinkedQueue<>();
    static ArrayDeque<Integer> d = new ArrayDeque<>();
    static LinkedList<Integer> e = new LinkedList<>();
    static long sink;

    static void fill() {
        for (int i = 0; i < WINDOW; i++) { a.enqueue(i); b.enqueue(i); c.enqueue(i); d.add(i); e.add(i); }
    }

    static long timeIt(Runnable body) {
        long best = Long.MAX_VALUE;
        for (int round = 0; round < 5; round++) {
            long t0 = System.nanoTime();
            body.run();
            best = Math.min(best, System.nanoTime() - t0);
        }
        return best;
    }

    static void row(String name, long t) {
        System.out.printf("%-28s %7d ms %7.1f%n", name, t / 1_000_000, t / (double) N);
    }

    public static void main(String[] args) {
        fill();
        System.out.printf("%-28s %10s %7s%n", "implementation", "time", "ns/op");

        row("int[] (fixed capacity)", timeIt(() -> {
            long s = 0;
            for (int i = 0; i < N; i++) { a.enqueue(i); s += a.dequeue(); }
            sink = s;
        }));
        row("ArrayQueue<Integer> (grows)", timeIt(() -> {
            long s = 0;
            for (int i = 0; i < N; i++) { b.enqueue(i); s += b.dequeue(); }
            sink = s;
        }));
        row("LinkedQueue<Integer>", timeIt(() -> {
            long s = 0;
            for (int i = 0; i < N; i++) { c.enqueue(i); s += c.dequeue(); }
            sink = s;
        }));
        row("java.util.ArrayDeque", timeIt(() -> {
            long s = 0;
            for (int i = 0; i < N; i++) { d.add(i); s += d.poll(); }
            sink = s;
        }));
        row("java.util.LinkedList", timeIt(() -> {
            long s = 0;
            for (int i = 0; i < N; i++) { e.add(i); s += e.poll(); }
            sink = s;
        }));
        if (sink == 42) System.out.println();
    }
}
