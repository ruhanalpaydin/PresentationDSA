import java.util.Arrays;

public class Bug5 {
    static int[] a = new int[3];
    static int head = 0, tail = 0, size = 0;

    static void enqueue(int x) {
        a[tail] = x; tail = (tail + 1) % a.length; size++;
    }

    static int dequeue() {
        int x = a[head]; head = (head + 1) % a.length; size--; return x;
    }

    static String show() {
        return Arrays.toString(a);   // BUG: the whole array, not the queue
    }

    public static void main(String[] args) {
        enqueue(10); enqueue(20); enqueue(30);
        dequeue();
        enqueue(40);
        System.out.println("queue: " + show());
    }
}
