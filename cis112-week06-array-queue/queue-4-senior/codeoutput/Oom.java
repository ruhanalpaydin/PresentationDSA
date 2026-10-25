import java.util.ArrayDeque;

/** An unbounded queue does not fail early: it fills the heap. */
public class Oom {
    public static void main(String[] args) {
        ArrayDeque<long[]> q = new ArrayDeque<>();
        long n = 0;
        try {
            while (true) { q.add(new long[1024]); n++; }   // 8 KB each
        } catch (OutOfMemoryError e) {
            q.clear();                                     // give the memory back
            System.gc();
            System.out.println("OutOfMemoryError after " + n + " arrays of 8 KB");
            System.out.println("message: " + e.getMessage());
        }
    }
}
