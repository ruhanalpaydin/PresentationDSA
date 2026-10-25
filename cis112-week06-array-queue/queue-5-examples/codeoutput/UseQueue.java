public class UseQueue {
    public static void main(String[] args) {
        ArrayQueue<String> desk = new ArrayQueue<>();
        desk.enqueue("Ali");
        desk.enqueue("Ayse");
        desk.enqueue("Mehmet");
        System.out.println("waiting: " + desk);

        String served = desk.dequeue();
        System.out.println("served " + served + " | " + desk);
        System.out.println("next: " + desk.peek());
        served = desk.dequeue();
        System.out.println("served " + served + " | " + desk);

        desk.enqueue("Zeynep");
        System.out.println("Zeynep joins | " + desk);
        while (!desk.isEmpty())
            System.out.println("served " + desk.dequeue() + " | " + desk);
        System.out.println("empty? " + desk.isEmpty());
    }
}
