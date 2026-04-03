
public class PG_20260403_12907_거스름돈 {

    class Solution {

        public int solution(int n, int[] money) {
            int[] dp = new int[n + 1];
            for (int m : money) {
                if (n < m) {
                    continue;
                }
                dp[m] = (dp[m] + 1) % 1_000_000_007;
                for (int i = m + 1; i <= n; i++) {
                    dp[i] = (dp[i] + dp[i - m]) % 1_000_000_007;
                }
            }
            return dp[n];
        }

    }

}
