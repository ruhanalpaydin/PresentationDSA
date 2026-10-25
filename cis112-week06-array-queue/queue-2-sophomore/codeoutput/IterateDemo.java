public class IterateDemo {
    public static void main(String[] args) {
        ArrayQueue<String> q = new ArrayQueue<>();
        for (String s : new String[] {"A", "B", "C", "D", "E"}) q.enqueue(s);  // grows once
        System.out.println("after 5 enqueues  = " + q + "   capacity=" + q.capacity());
        q.dequeue(); q.dequeue();                       // head is now at index 2
        q.enqueue("F"); q.enqueue("G"); q.enqueue("H"); // tail wraps
        System.out.println("after the wrap    = " + q);
        System.out.print("for-each          =");
        for (String s : q) System.out.print(" " + s);
        System.out.println();
    }
}
