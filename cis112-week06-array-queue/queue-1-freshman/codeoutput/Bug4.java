public class Bug4 {
    static int[] a = new int[3];
    static int head = 0, tail = 0, size = 0;

    static void enqueue(int x) {
        if (tail == a.length) throw new IllegalStateException("overflow"); // BUG: tail wrapped to 0
        a[tail] = x; tail = (tail + 1) % a.length; size++;
    }

    static String show() {
        StringBuilder sb = new StringBuilder("front [");
        for (int i = 0; i < size; i++) sb.append(i > 0 ? ", " : "").append(a[(head + i) % a.length]);
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        enqueue(10); enqueue(20); enqueue(30);
        System.out.println("full: " + show() + "  size = " + size);
        enqueue(40);            // no exception: it overwrites 10
        System.out.println("after enqueue(40): " + show() + "  size = " + size);
    }
}
