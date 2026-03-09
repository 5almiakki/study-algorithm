
public class PG_20260309_12914_멀리_뛰기 {

    class Solution {

        public long solution(int n) {
            if (n == 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
            }
            return dp[n];
        }

    }

}
