import java.util.*;

public class PG_20260128_12983_단어_퍼즐 {

    class Solution {

        public int solution(String[] strs, String t) {
            int length = t.length();
            int[] dp = new int[length + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            for (int substringLength = 1; substringLength <= length; substringLength++) {
                String substring = t.substring(0, substringLength);
                for (String str : strs) {
                    if (!substring.endsWith(str)) {
                        continue;
                    }
                    if (substring.equals(str)) {
                        dp[str.length()] = 1;
                        break;
                    }
                    int subsubstringLength = substringLength - str.length();
                    if (dp[subsubstringLength] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[substringLength] = Math.min(dp[substringLength], dp[subsubstringLength] + 1);
                }
            }
            return dp[length] == Integer.MAX_VALUE ? -1 : dp[length];
        }

    }

}
