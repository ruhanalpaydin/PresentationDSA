import java.util.PriorityQueue;

public class Priority {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : new int[] {30, 10, 20, 5, 40}) pq.offer(x);
        System.out.println("heap print = " + pq);   // not the order of leaving
        System.out.print("poll order =");
        while (!pq.isEmpty()) System.out.print(" " + pq.poll());
        System.out.println();
    }
}
