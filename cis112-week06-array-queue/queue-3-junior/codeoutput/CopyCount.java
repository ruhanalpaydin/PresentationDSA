/** How many element copies does a doubling queue do? */
public class CopyCount {
    public static void main(String[] args) {
        int n = 16;
        int capacity = 1, size = 0, total = 0;
        System.out.println(" enqueue  capacity  copied  total");
        for (int i = 1; i <= n; i++) {
            int copied = 0;
            if (size == capacity) {
                copied = size;
                total += copied;
                capacity *= 2;
            }
            size++;
            System.out.printf("%8d %9d %7d %6d%n", i, capacity, copied, total);
        }
        System.out.println("copies < 2n: " + total + " < " + (2 * n));
    }
}
