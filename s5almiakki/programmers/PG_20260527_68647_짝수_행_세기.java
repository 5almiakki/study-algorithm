
public class PG_20260527_68647_짝수_행_세기 {

    class Solution {

        // static final String LN = System.lineSeparator();
        static final long DIVISOR = 10_000_019L;
        static final long[][] COMBINATION = new long[301][301];

        static {
            COMBINATION[0][0] = 1L;
            for (int i = 1; i < 301; i++) {
                COMBINATION[i][0] = 1L;
                for (int j = 1; j <= i; j++) {
                    COMBINATION[i][j] = (COMBINATION[i - 1][j] + COMBINATION[i - 1][j - 1]) % DIVISOR;
                }
            }
        }

        public int solution(int[][] a) {
            if (a[0].length == 1) {
                for (int[] row : a) {
                    if (row[0] == 1) {
                        return 0;
                    }
                }
                return 1;
            }

            int rowCount = a.length;
            int colCount = a[0].length;
            int[] oneCounts = new int[colCount];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    oneCounts[j] += a[i][j];
                }
            }
            // System.out.println(Arrays.toString(oneCounts));

            long[][] dp = new long[colCount][rowCount + 1];
            dp[0][rowCount - oneCounts[0]] = COMBINATION[rowCount][rowCount - oneCounts[0]];
            // System.out.println("col: 0, oneCount: " + oneCounts[0]);
            // System.out.println(to2dArrayString(dp));
            for (int col = 1; col < colCount; col++) {
                // System.out.println("col: " + col + ", oneCount: " + oneCounts[col]);
                for (int prevEvenCount = 0; prevEvenCount <= rowCount; prevEvenCount++) {
                    if (dp[col - 1][prevEvenCount] == 0L) {
                        continue;
                    }
                    int prevOddCount = rowCount - prevEvenCount;
                    for (int evenOverlapCount = 0; evenOverlapCount <= oneCounts[col]; evenOverlapCount++) {
                        int evenCount = prevEvenCount + oneCounts[col] - (evenOverlapCount << 1);
                        if (rowCount < evenCount || prevEvenCount < evenOverlapCount) {
                            continue;
                        }
                        long caseCount = (
                                COMBINATION[prevEvenCount][evenOverlapCount]
                                        * COMBINATION[prevOddCount][oneCounts[col] - evenOverlapCount]
                                ) % DIVISOR;
                        dp[col][evenCount] = (
                                dp[col][evenCount] + (
                                        dp[col - 1][prevEvenCount] * caseCount
                                        ) % DIVISOR
                                ) % DIVISOR;
                    }
                }
                // System.out.println(to2dArrayString(dp));
            }
            return (int) dp[colCount - 1][rowCount];
        }

        // String to2dArrayString(int[][] dp) {
        //     StringBuilder result = new StringBuilder();
        //     for (int[] row : dp) {
        //         result.append(Arrays.toString(row)).append(LN);
        //     }
        //     return result.toString();
        // }

    }

}
