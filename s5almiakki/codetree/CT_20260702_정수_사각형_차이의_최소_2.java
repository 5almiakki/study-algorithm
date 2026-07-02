import java.io.*;

public class CT_20260702_정수_사각형_차이의_최소_2 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int gridSize = Integer.parseInt(br.readLine());
            int[][] grid = new int[gridSize][gridSize];
            int maxCell = 0;
            for (int row = 0; row < gridSize; row++) {
                String[] input = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    int cell = Integer.parseInt(input[col]);
                    grid[row][col] = cell;
                    if (maxCell < cell) {
                        maxCell = cell;
                    }
                }
            }
            int[][] dp = new int[gridSize][gridSize];
            int answer = Integer.MAX_VALUE;
            // 최솟값이 lowerBound 이상인 경로를 탐색
            // 가장 밖의 반복 1회 내에서
            // lowerBound는 고정, 경로 상의 최댓값만 dp에 갱신
            for (int lowerBound = 1; lowerBound <= maxCell; lowerBound++) {
                disableIfLower(grid, 0, 0, lowerBound);
                dp[0][0] = grid[0][0];
                for (int col = 1; col < gridSize; col++) {
                    disableIfLower(grid, 0, col, lowerBound);
                    dp[0][col] = Math.max(dp[0][col - 1], grid[0][col]);
                }
                for (int row = 1; row < gridSize; row++) {
                    disableIfLower(grid, row, 0, lowerBound);
                    dp[row][0] = Math.max(dp[row - 1][0], grid[row][0]);
                    for (int col = 1; col < gridSize; col++) {
                        disableIfLower(grid, row, col, lowerBound);
                        // 현재 셀의 위, 왼쪽 셀 중 작은 걸 골라
                        // 지금까지의 경로 상 최댓값으로 갱신
                        dp[row][col] = Math.max(
                                Math.min(dp[row - 1][col], dp[row][col - 1]),
                                grid[row][col]
                        );
                    }
                }
                int idx = gridSize - 1;
                int result = dp[idx][idx] - lowerBound;
                if (result < answer) {
                    answer = result;
                }
            }
            System.out.print(answer);
        }

        // 셀 값이 lowerBound 미만이면 셀을 비활성화해
        // 해당 셀을 거치는 경로를 제외
        static void disableIfLower(int[][] grid, int row, int col, int lowerBound) {
            if (grid[row][col] < lowerBound) {
                grid[row][col] = Integer.MAX_VALUE;
            }
        }

    }

}
