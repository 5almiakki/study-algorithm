
public class PG_20260211_42898_등굣길 {

    class Solution {

        public int solution(int m, int n, int[][] puddles) {
            int[][] dp = new int[m][n];
            for (int[] puddle : puddles) {
                dp[puddle[0] - 1][puddle[1] - 1] = -1;
            }

            for (int col = 0; col < n; col++) {
                if (dp[0][col] == -1) {
                    break;
                }
                dp[0][col] = 1;
            }
            for (int row = 0; row < m; row++) {
                if (dp[row][0] == -1) {
                    break;
                }
                dp[row][0] = 1;
            }

            for (int row = 1; row < m; row++) {
                for (int col = 1; col < n; col++) {
                    if (dp[row][col] == -1) {
                        continue;
                    }
                    int up = dp[row - 1][col];
                    up = (up == -1) ? 0 : up;
                    int left = dp[row][col - 1];
                    left = (left == -1) ? 0 : left;
                    dp[row][col] = (up + left) % 1_000_000_007;
                }
            }
            return dp[m - 1][n - 1];
        }

    }

}
