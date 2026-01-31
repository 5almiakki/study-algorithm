import java.io.*;
import java.util.*;

public class BOJ_20260131_12100_2048_Easy {

    public static class Main {

        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };
        static final int[][] REVERSE_DELTAS = {
                { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
        };
        static final int[][] BASES = {
                { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }
        };
        static final int[][] BASE_DELTAS = {
                { 0, 1 }, { 0, 1 }, { 1, 0 }, { 1, 0 }
        };

        public static void main(String[] args) throws IOException {
            int size = 0;
            int[][] grid = null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                size = Integer.parseInt(br.readLine());
                BASES[1][0] = size - 1;
                BASES[3][1] = size - 1;


                grid = new int[size][size];
                for (int row = 0; row < size; row++) {
                    String[] splitted = br.readLine().split(" ");
                    for (int col = 0; col < size; col++) {
                        grid[row][col] = Integer.parseInt(splitted[col]);
                    }
                }
            }
            System.out.print(dfs(grid, 0));
        }

        static int dfs(int[][] grid, int moveCount) {
            if (moveCount == 5) {
                return max(grid);
            }

            int result = max(grid);
            for (int direction = 0; direction < 4; direction++) {
                int[][] newGrid = new int[grid.length][grid.length];

                int baseRow = BASES[direction][0];
                int baseCol = BASES[direction][1];
                for (int i = 0; i < grid.length; i++) {
                    int row = baseRow;
                    int col = baseCol;
                    Queue<Integer> queue = new ArrayDeque<>();
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[row][col] != 0) {
                            queue.add(grid[row][col]);
                        }
                        row += REVERSE_DELTAS[direction][0];
                        col += REVERSE_DELTAS[direction][1];
                    }

                    row = baseRow;
                    col = baseCol;
                    while (!queue.isEmpty()) {
                        Integer num = queue.remove();
                        Integer nextNum = queue.peek();
                        if (num.equals(nextNum)) {
                            queue.remove();
                            newGrid[row][col] = num << 1;
                        } else {
                            newGrid[row][col] = num;
                        }
                        row += REVERSE_DELTAS[direction][0];
                        col += REVERSE_DELTAS[direction][1];
                    }

                    baseRow += BASE_DELTAS[direction][0];
                    baseCol += BASE_DELTAS[direction][1];
                }

                boolean different = false;
                for (int row = 0; row < grid.length; row++) {
                    if (!Arrays.equals(grid[row], newGrid[row])) {
                        different = true;
                        break;
                    }
                }
                if (different) {
                    int newResult = dfs(newGrid, moveCount + 1);
                    if (result < newResult) {
                        result = newResult;
                    }
                }
            }
            return result;
        }

        static int max(int[][] grid) {
            int result = 0;
            for (int[] row : grid) {
                for (int cell : row) {
                    if (result < cell) {
                        result = cell;
                    }
                }
            }
            return result;
        }

    }

}
