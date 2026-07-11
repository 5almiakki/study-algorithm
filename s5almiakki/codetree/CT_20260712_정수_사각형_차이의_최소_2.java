import java.io.*;
import java.util.*;

public class CT_20260712_정수_사각형_차이의_최소_2 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int gridSize = Integer.parseInt(br.readLine());
            int[][] grid = new int[gridSize][gridSize];
            SortedSet<Integer> values = new TreeSet<>();
            for (int row = 0; row < gridSize; row++) {
                String[] input = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    int cell = Integer.parseInt(input[col]);
                    values.add(cell);
                    grid[row][col] = cell;
                }
            }

            int answer = Integer.MAX_VALUE;
            // min 미만의 셀은 못 지나간다고 가정하고
            // min을 증가시키며 가능한 경우를 탐색
            for (int min : values) {
                int[][] dp = new int[gridSize][gridSize];
                disableIfLower(min, grid, 0, 0);
                dp[0][0] = grid[0][0];
                for (int col = 1; col < gridSize; col++) {
                    disableIfLower(min, grid, 0, col);
                    dp[0][col] = Math.max(dp[0][col - 1], grid[0][col]);
                }
                for (int row = 1; row < gridSize; row++) {
                    disableIfLower(min, grid, row, 0);
                    dp[row][0] = Math.max(dp[row - 1][0], grid[row][0]);
                    for (int col = 1; col < gridSize; col++) {
                        disableIfLower(min, grid, row, col);
                        //
                        dp[row][col] = Math.max(
                                Math.min(dp[row - 1][col], dp[row][col - 1]),
                                grid[row][col]
                        );
                    }
                }
                answer = Math.min(answer, dp[gridSize - 1][gridSize - 1] - min);
            }
            System.out.print(answer);
        }

        static void disableIfLower(int value, int[][] grid, int row, int col) {
            if (value > grid[row][col]) {
                grid[row][col] = Integer.MAX_VALUE;
            }
        }

    }

}
