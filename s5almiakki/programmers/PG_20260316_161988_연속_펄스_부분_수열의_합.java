
public class PG_20260316_161988_연속_펄스_부분_수열의_합 {

    class Solution {

        public long solution(int[] sequence) {
            long answer = Long.MIN_VALUE;
            for (long initialSign = -1L; initialSign <= 1L; initialSign += 2L) {
                long sign = initialSign;
                long sum = sign * sequence[0];
                long minSum = sum;
                if (answer < sum) {
                    answer = sum;
                }
                for (int j = 1; j < sequence.length; j++) {
                    sign = -sign;
                    sum += sign * sequence[j];
                    long max = Math.max(sum, sum - minSum);
                    if (answer < max) {
                        answer = max;
                    }
                    if (sum < minSum) {
                        minSum = sum;
                    }
                }
            }
            return answer;
        }

    }

}
