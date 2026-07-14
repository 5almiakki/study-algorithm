import java.io.*;
import java.util.*;

public class CT_20260714_정수_사각형_최장_증가_수열 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int gridSize = Integer.parseInt(br.readLine());
            int[][] grid = new int[gridSize][gridSize];
            List<int[]> cells = new ArrayList<>();
            for (int row = 0; row < gridSize; row++) {
                String[] input = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    int cell = Integer.parseInt(input[col]);
                    grid[row][col] = cell;
                    cells.add(new int[] { row, col, cell });
                }
            }
            cells.sort((c1, c2) -> Integer.compare(c1[2], c2[2]));
            int[][] deltas = {
                    { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
            };
            int[][] dp = new int[gridSize][gridSize];
            int answer = 0;
            for (int[] cell : cells) {
                for (int[] delta : deltas) {
                    int adjRow = cell[0] + delta[0];
                    int adjCol = cell[1] + delta[1];
                    if (isOutOfBounds(gridSize, adjRow, adjCol)
                            || grid[cell[0]][cell[1]] <= grid[adjRow][adjCol]) {
                        continue;
                    }
                    dp[cell[0]][cell[1]] = Math.max(dp[cell[0]][cell[1]], dp[adjRow][adjCol]);
                }
                dp[cell[0]][cell[1]]++;
                answer = Math.max(answer, dp[cell[0]][cell[1]]);
            }
            // for (int[] row : dp) {
            //     System.out.println(Arrays.toString(row));
            // }
            System.out.print(answer);
        }

        static boolean isOutOfBounds(int gridSize, int row, int col) {
            return row < 0 || gridSize <= row
                    || col < 0 || gridSize <= col;
        }

    }

}
