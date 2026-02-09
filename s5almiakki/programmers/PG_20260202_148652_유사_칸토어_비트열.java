
public class PG_20260202_148652_유사_칸토어_비트열 {

    class Solution {

        // static final int[] REMAINDER_TO_BIT_MAP = { 1, 1, 0, 1, 1 };
        // static final String LN = System.lineSeparator();

        // final StringBuilder log = new StringBuilder();

        public int solution(int n, long l, long r) {
            long maxDivisor = 1;
            for (int i = 1; i < n; i++) {
                maxDivisor *= 5L;
            }

            // StringBuilder substring = new StringBuilder();
            int answer = 0;
            for (long idx = l - 1L; idx < r; idx++) {
                long dividend = idx;
                boolean isZero = false;
                for (long divisor = maxDivisor; divisor >= 1L; divisor /= 5L) {
                    if (dividend / divisor == 2) {
                        isZero = true;
                        break;
                    }
                    dividend %= divisor;
                }
                // substring.append(isZero ? '0' : '1');
                if (!isZero) {
                    answer++;
                }
            }
            // System.out.print(log.append(substring));
            return answer;
        }

    }

}
