public class Bug1 {
    static int[] a = new int[3];
    static int head = 0, tail = 0, size = 0;

    static void enqueue(int x) {
        a[tail++] = x;          // BUG: tail must wrap around
        size++;
    }

    static int dequeue() { int x = a[head++]; size--; return x; }

    public static void main(String[] args) {
        enqueue(10); enqueue(20); enqueue(30);
        System.out.println("dequeue = " + dequeue());
        enqueue(40);            // tail is 3: there is no a[3]
    }
}
