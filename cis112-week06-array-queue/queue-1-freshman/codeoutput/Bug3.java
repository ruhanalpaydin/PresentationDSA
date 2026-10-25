public class Bug3 {
    static int[] a = new int[3];
    static int head = 0, tail = 0, size = 0;

    static void enqueue(int x) {
        a[tail] = x; tail = (tail + 1) % a.length; size++;
    }

    static int dequeue() {
        int x = a[head];        // BUG: no check for an empty queue
        head = (head + 1) % a.length; size--;
        return x;
    }

    public static void main(String[] args) {
        enqueue(10);
        System.out.println("dequeue = " + dequeue());
        System.out.println("dequeue = " + dequeue());
    }
}
