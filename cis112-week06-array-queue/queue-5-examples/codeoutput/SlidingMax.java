import java.util.ArrayDeque;

/** The maximum of every window of k numbers, in one pass, with a deque. */
public class SlidingMax {
    public static void main(String[] args) {
        int[] x = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        ArrayDeque<Integer> d = new ArrayDeque<>();   // indices, values decreasing

        System.out.print("window maxima:");
        for (int i = 0; i < x.length; i++) {
            while (!d.isEmpty() && x[d.peekLast()] <= x[i]) d.pollLast();
            d.addLast(i);
            if (d.peekFirst() <= i - k) d.pollFirst();
            if (i >= k - 1) System.out.print("  " + x[d.peekFirst()]);
        }
        System.out.println();
    }
}
