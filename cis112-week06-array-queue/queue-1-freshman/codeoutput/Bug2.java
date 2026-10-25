public class Bug2 {
    static int[] a = new int[4];
    static int head = 0, tail = 0, size = 0;

    static void enqueue(int x) {
        a[tail] = x; tail = (tail + 1) % a.length; size++;
    }

    static int dequeue() {
        return a[head];         // BUG: head never moves
    }

    public static void main(String[] args) {
        enqueue(10); enqueue(20);
        System.out.println("dequeue = " + dequeue());
        System.out.println("dequeue = " + dequeue());
        System.out.println("dequeue = " + dequeue());
    }
}
