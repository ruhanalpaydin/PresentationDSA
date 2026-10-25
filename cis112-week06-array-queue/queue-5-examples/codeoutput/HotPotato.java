import java.util.ArrayDeque;
import java.util.List;

/** Pass the potato three times; the holder leaves. The last child wins. */
public class HotPotato {
    public static void main(String[] args) {
        ArrayDeque<String> circle = new ArrayDeque<>(
                List.of("Ali", "Ayse", "Mehmet", "Zeynep", "Can", "Ece"));
        int passes = 3;

        while (circle.size() > 1) {
            for (int i = 0; i < passes; i++) circle.addLast(circle.removeFirst());
            System.out.printf("%-8s is out   left: %s%n", circle.removeFirst(), circle);
        }
        System.out.println("winner: " + circle.removeFirst());
    }
}
