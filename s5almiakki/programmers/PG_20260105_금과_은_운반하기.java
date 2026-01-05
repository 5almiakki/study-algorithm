
public class PG_20260105_금과_은_운반하기 {

    class Solution {

        public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
            long low = 0L;
            long high = 400_000_000_000_000L;
            long answer = high;
            do {
                // System.out.println();
                long mid = (low >> 1L) + (high >> 1L);
                if (low % 2L == 1L && high % 2L == 1L) {
                    mid++;
                }
                // long mid = (low + high) >> 1L;
                // System.out.println("low=" + low);
                // System.out.println("mid=" + mid);
                // System.out.println("high=" + high);

                long total = 0L;
                long totalGold = 0L;
                long totalSilver = 0L;
                for (int city = 0; city < g.length; city++) {
                    long deliveryCount = (mid + t[city]) / (t[city] << 1L);
                    long maxDeliverableAmount = Math.min(w[city] * deliveryCount, g[city] + s[city]);
                    // System.out.println("  deliveryCount=" + deliveryCount);
                    // System.out.println("  maxDeliverableAmount=" + maxDeliverableAmount);
                    total += maxDeliverableAmount;
                    totalGold += Math.min(maxDeliverableAmount, g[city]);
                    totalSilver += Math.min(maxDeliverableAmount, s[city]);
                }
                // System.out.println("total=" + total);
                // System.out.println("totalGold=" + totalGold);
                // System.out.println("totalSilver=" + totalSilver);

                if (total < a + b || totalGold < a || totalSilver < b) {
                    low = mid + 1L;
                } else {
                    answer = Math.min(answer, mid);
                    high = mid - 1L;
                }
            } while (low <= high);

            return answer;
        }

    }

}
