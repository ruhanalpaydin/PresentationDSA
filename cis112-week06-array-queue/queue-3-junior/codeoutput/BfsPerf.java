import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/** BFS over a grid, with two queue implementations. */
public class BfsPerf {
    static final int ROWS = 800, COLS = 800;
    static final int[] DR = {-1, 1, 0, 0}, DC = {0, 0, -1, 1};

    static int bfs(Queue<Integer> queue) {
        boolean[] seen = new boolean[ROWS * COLS];
        queue.add(0);
        seen[0] = true;
        int visited = 0;
        while (!queue.isEmpty()) {
            int cell = queue.poll();
            visited++;
            int r = cell / COLS, c = cell % COLS;
            for (int k = 0; k < 4; k++) {
                int nr = r + DR[k], nc = c + DC[k];
                if (nr < 0 || nr >= ROWS || nc < 0 || nc >= COLS) continue;
                int next = nr * COLS + nc;
                if (!seen[next]) { seen[next] = true; queue.add(next); }
            }
        }
        return visited;
    }

    public static void main(String[] args) {
        long t0 = System.nanoTime();
        int v1 = bfs(new ArrayDeque<>());
        long t1 = System.nanoTime();
        int v2 = bfs(new LinkedList<>());
        long t2 = System.nanoTime();
        System.out.printf("ArrayDeque  %4d ms   visited %d%n", (t1 - t0) / 1_000_000, v1);
        System.out.printf("LinkedList  %4d ms   visited %d%n", (t2 - t1) / 1_000_000, v2);
        System.out.println("same answer: " + (v1 == v2));
    }
}
