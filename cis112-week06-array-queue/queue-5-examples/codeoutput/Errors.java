public class Errors {
    public static void main(String[] args) {
        IntQueue q = new IntQueue(3);
        try {
            q.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("dequeue on empty -> " + e.getMessage());
        }
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        try {
            q.enqueue(4);
        } catch (IllegalStateException e) {
            System.out.println("enqueue on full  -> " + e.getMessage());
        }
        System.out.println("queue = " + q + "   size = " + q.size());
    }
}
