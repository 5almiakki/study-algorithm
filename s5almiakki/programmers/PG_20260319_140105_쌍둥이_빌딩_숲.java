//import java.util.*;

public class PG_20260319_140105_쌍둥이_빌딩_숲 {

    class Solution {

        public int solution(int n, int count) {
            long[] dp = new long[n + 1];
            dp[1] = 1L;
            for (int buildingCount = 2; buildingCount <= n; buildingCount++) {
                for (int colorCount = buildingCount; colorCount >= 1; colorCount--) {
                    dp[colorCount] = (dp[colorCount - 1]
                            + dp[colorCount] * (((long) buildingCount - 1L) << 1L)) % 1_000_000_007L;
                }
                // System.out.println(Arrays.toString(dp));
            }
            // System.out.println(Arrays.toString(dp));
            return (int) dp[count];
        }

    }

    /*
    n = 1 count = 1
    99

    n = 2 count = 2
    8899 (n = 1 count = 1)
    n = 2 count = 1
    9988 9889 (n = 1 count = 1) * 2

    n = 3 count = 3
    778899 (n = 2 count = 2) = 1
    n = 3 count = 2
    779988 779889 (n = 2 count = 1)
    889977 889779 887799 877899 (n = 2 count = 2) * 4
    n = 3 count = 1
    998877 998778 997788 977988
    988977 988779 987789 977889 (n = 2 count = 1) * 2 * 2

    n = 4 count = 4
    66778899 (n = 3 count = 3)
    n = 4 count = 3
    (n = 3 count = 2)
    (n = 3 count = 3) * (n - 1) * 3 * 2
     */

}
