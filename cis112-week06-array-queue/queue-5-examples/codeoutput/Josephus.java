import java.util.ArrayDeque;

/** The Josephus problem: n people in a circle, every k-th one leaves. */
public class Josephus {
    public static void main(String[] args) {
        int n = 7, k = 3;
        ArrayDeque<Integer> circle = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) circle.add(i);

        StringBuilder order = new StringBuilder();
        int count = 1;
        while (circle.size() > 1) {
            int person = circle.poll();
            if (count == k) {
                order.append(person).append(' ');
                count = 1;
            } else {
                circle.add(person);
                count++;
            }
        }
        System.out.println("n = " + n + ", k = " + k);
        System.out.println("leave: " + order.toString().trim());
        System.out.println("survivor: " + circle.poll());
    }
}
