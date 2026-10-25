public class RingDemo {
    public static void main(String[] args) {
        RingBuffer<Integer> log = new RingBuffer<>(4);   // keep the last 4 readings
        for (int i = 1; i <= 6; i++) {
            log.put(i);
            System.out.printf("put %d -> %-14s size=%d dropped=%d%n", i, log, log.size(), log.dropped());
        }
        System.out.println("take -> " + log.take() + "   left " + log);
    }
}
