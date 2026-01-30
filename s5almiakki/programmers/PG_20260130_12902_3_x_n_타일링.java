
public class PG_20260130_12902_3_x_n_타일링 {

    class Solution {

        public int solution(int n) {
            if (n % 2 == 1) {
                return 0;
            } else if (n == 2) {
                return 3;
            } else if (n == 4) {
                return 11;
            }

            n >>= 1;
            long[] dp = new long[n + 1];
            dp[0] = 1L;
            dp[1] = 3L;
            for (int i = 2; i < dp.length; i++) {
                dp[i] = (
                        1_000_000_007L + (dp[i - 1] << 2L) - (dp[i - 2] % 1_000_000_007L)
                        ) % 1_000_000_007L;
            }
            return (int) dp[n];
        }

    }

}
