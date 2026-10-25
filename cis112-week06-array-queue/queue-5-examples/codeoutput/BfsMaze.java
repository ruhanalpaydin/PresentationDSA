import java.util.ArrayDeque;
import java.util.Arrays;

/** Shortest path in a maze: breadth-first search with a queue. */
public class BfsMaze {
    static final String[] MAZE = {
        "........",
        ".##.###.",
        "........",
        "###.#...",
        "....#..#",
        ".##...#.",
        "...#....",
        "........",
    };

    public static void main(String[] args) {
        int rows = MAZE.length, cols = MAZE[0].length();
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, -1);

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        dist[0][0] = 0;
        queue.add(new int[] {0, 0});
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int r = cur[0] + dr[k], c = cur[1] + dc[k];
                if (r < 0 || r >= rows || c < 0 || c >= cols) continue;
                if (MAZE[r].charAt(c) == '#') continue;
                if (dist[r][c] != -1) continue;
                dist[r][c] = dist[cur[0]][cur[1]] + 1;
                queue.add(new int[] {r, c});
            }
        }

        System.out.println("distance from (0,0); # = wall, . = not reachable");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (MAZE[r].charAt(c) == '#') System.out.printf("%4s", "#");
                else if (dist[r][c] < 0)      System.out.printf("%4s", ".");
                else                          System.out.printf("%4d", dist[r][c]);
            }
            System.out.println();
        }
        System.out.println("shortest distance to (7,7) = " + dist[7][7]);
    }
}
