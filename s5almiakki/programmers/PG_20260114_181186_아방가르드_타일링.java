
public class PG_20260114_181186_아방가르드_타일링 {

    class Solution {

        public int solution(int n) {
            switch (n) {
                case 1:
                    return 1;
                case 2:
                    return 3;
                case 3:
                    return 10;
                case 4:
                    return 23;
                case 5:
                    return 62;
            }

            long[] dp = new long[n + 1];
            long divisor = 1_000_000_007L;
            dp[0] = 1L;
            dp[1] = 1L;
            dp[2] = 3L;
            dp[3] = 10L;
            dp[4] = 23L;
            dp[5] = 62L;

            for (int base = 6; base < dp.length; base++) {
                // dp[base] = (dp[base - 1]
                //     + ((dp[base - 3] * 5L) % divisor)) % divisor;
                // for (int offset = -2; base + offset >= 0; offset -= 3) {
                //     dp[base] = (
                //         dp[base]
                //         + ((dp[base + offset] << 1L) % divisor)
                //         ) % divisor;
                // }
                // for (int offset = -4; base + offset >= 0; offset -= 3) {
                //     dp[base] = (
                //         dp[base]
                //         + ((dp[base + offset] << 1L) % divisor)
                //         ) % divisor;
                // }
                // for (int offset = -6; base + offset >= 0; offset -= 3) {
                //     dp[base] = (
                //         dp[base]
                //         + ((dp[base + offset] << 2L) % divisor)
                //         ) % divisor;
                // }
                dp[base] = (dp[base - 1] + (dp[base - 2] << 1)) % divisor;
                dp[base] = (dp[base] + (6 * dp[base - 3])) % divisor;
                dp[base] = (dp[base] + dp[base - 4]) % divisor;
                dp[base] = (dp[base] - dp[base - 6] + divisor) % divisor;
            }
            return (int) (dp[n] % divisor);
        }

    }

    /*
    a0 = 1
    a1 = 1
    a2 = 3
    a3 = 10
    a4 = 23
    a5 = 62

    an        = an-1 + 2an-2 + 5an-3 + 2an-4 + 2an-5 + 4an-6 + 2an-7 + 2an-8 + 4an-9 + ...
    an-3      =                         an-4 + 2an-5 + 5an-6 + 2an-7 + 2an-8 + 4an-9 + ...
    an - an-3 = an-1 + 2an-2 + 5an-3 +  an-4         - an-6
    an        = an-1 + 2an-2 + 6an-3 +  an-4         - an-6
     */

}
