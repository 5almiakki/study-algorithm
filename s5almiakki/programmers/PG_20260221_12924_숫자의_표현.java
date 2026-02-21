
public class PG_20260221_12924_숫자의_표현 {

    class Solution {

        public int solution(int n) {
            int answer = 1;
            for (int divisor = 2; divisor < n; divisor++) {
                if (divisor % 2 == 1) {
                    if (n % divisor == 0) {
                        answer++;
                    }
                    continue;
                }
                if (n % divisor == 1 && (n << 1) % divisor == 0) {
                    answer++;
                }
            }
            return answer;
        }

    }

}
