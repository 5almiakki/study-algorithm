import java.io.*;
import java.util.*;

public class CT_20260706_정수_사각형_최장_증가_수열 {

    public class Main {

        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int gridSize = Integer.parseInt(br.readLine());
            int[][] grid = new int[gridSize][gridSize];
            List<int[]> points = new ArrayList<>(); // { cell, row, col }
            for (int row = 0; row < gridSize; row++) {
                String[] input = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    int cell = Integer.parseInt(input[col]);
                    grid[row][col] = cell;
                    points.add(new int[] { cell, row, col });
                }
            }
            points.sort((p1, p2) -> Integer.compare(p1[0], p2[0]));
            int answer = 0;
            int[][] dp = new int[gridSize][gridSize];
            for (int[] point : points) {
                int newHopCount = dp[point[1]][point[2]] + 1;
                for (int[] delta : DELTAS) {
                    int newRow = point[1] + delta[0];
                    int newCol = point[2] + delta[1];
                    if (isOutOfBounds(gridSize, newRow, newCol)
                            || grid[point[1]][point[2]] >= grid[newRow][newCol]
                            || dp[newRow][newCol] >= newHopCount) {
                        continue;
                    }
                    dp[newRow][newCol] = newHopCount;
                    if (answer < newHopCount) {
                        answer = newHopCount;
                    }
                }
            }
            System.out.print(answer + 1);
        }

        static boolean isOutOfBounds(int gridSize, int row, int col) {
            return row < 0 || gridSize <= row
                    || col < 0 || gridSize <= col;
        }

    }

}
