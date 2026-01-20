
public class PG_20260120_49995_쿠키_구입 {

    class Solution {

        public int solution(int[] cookie) {
            if (cookie.length < 2) {
                return 0;
            }

            int answer = 0;
            for (int m = 0; m < cookie.length - 1; m++) {
                int sum1 = 0;
                int l = 0;
                for (int i = l; i <= m; i++) {
                    sum1 += cookie[i];
                }
                if (sum1 < answer) {
                    continue;
                }

                int sum2 = 0;
                int r = cookie.length - 1;
                for (int i = m + 1; i <= r; i++) {
                    sum2 += cookie[i];
                }
                if (sum2 < answer) {
                    continue;
                }

                do {
                    if (r <= m || m < l) {
                        break;
                    }
                    if (sum1 < sum2) {
                        sum2 -= cookie[r];
                        r--;
                        continue;
                    }
                    if (sum1 > sum2) {
                        sum1 -= cookie[l];
                        l++;
                        continue;
                    }
                    answer = Math.max(answer, sum1);
                    break;
                } while (true);
            }

            return answer;
        }

    }

}
