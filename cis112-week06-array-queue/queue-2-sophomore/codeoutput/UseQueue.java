public class UseQueue {
    public static void main(String[] args) {
        QueueADT<String> q = new ArrayQueue<>();   // program to the interface
        q.enqueue("Ali");
        q.enqueue("Ayse");
        q.enqueue("Mehmet");
        System.out.println("waiting = " + q + "   size = " + q.size());
        System.out.println("served  = " + q.dequeue() + "   waiting = " + q);
        System.out.println("front   = " + q.peek());
        System.out.println("empty?  = " + q.isEmpty());
    }
}
