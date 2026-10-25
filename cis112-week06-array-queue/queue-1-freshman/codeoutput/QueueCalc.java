import java.util.Scanner;

/** A queue of int that you drive from the keyboard. */
public class QueueCalc {
    public static void main(String[] args) {
        IntQueue q = new IntQueue(3);
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) continue;
            System.out.println("> " + line);
            String[] p = line.split("\\s+");
            try {
                switch (p[0]) {
                    case "enqueue" -> { q.enqueue(Integer.parseInt(p[1])); System.out.println("  " + q); }
                    case "dequeue" -> System.out.println("  " + q.dequeue() + "   " + q);
                    case "peek"    -> System.out.println("  " + q.peek() + "   " + q);
                    case "size"    -> System.out.println("  " + q.size());
                    case "empty"   -> System.out.println("  " + q.isEmpty());
                    case "quit"    -> { return; }
                    default        -> System.out.println("  unknown command: " + p[0]);
                }
            } catch (IllegalStateException e) {
                System.out.println("  error: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("  usage: enqueue <number>");
            }
        }
    }
}
