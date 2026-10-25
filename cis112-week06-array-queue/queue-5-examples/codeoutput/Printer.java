import java.util.ArrayDeque;

/**
 * One printer, one queue of jobs, one page per time unit.
 * The arrivals and lengths are fixed, so the table is the same on every run.
 */
public class Printer {
    record Job(int id, int arrival, int pages) { }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job(1, 0, 3), new Job(2, 1, 1), new Job(3, 2, 4),
            new Job(4, 5, 2), new Job(5, 6, 2), new Job(6, 9, 1)
        };
        ArrayDeque<Job> queue = new ArrayDeque<>();
        int next = 0, busyUntil = 0, totalWait = 0;

        System.out.println("job  arrives  pages  starts  finishes  waits");
        for (int t = 0; t <= 20; t++) {
            while (next < jobs.length && jobs[next].arrival() == t)
                queue.add(jobs[next++]);
            if (t >= busyUntil && !queue.isEmpty()) {
                Job j = queue.poll();
                int wait = t - j.arrival();
                busyUntil = t + j.pages();
                totalWait += wait;
                System.out.printf("%3d %7d %6d %7d %9d %6d%n",
                        j.id(), j.arrival(), j.pages(), t, busyUntil, wait);
            }
        }
        System.out.printf("average wait = %.1f time units%n", totalWait / (double) jobs.length);
    }
}
