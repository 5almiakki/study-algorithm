
public class PG_20260225_12938_최고의_집합 {

    class Solution {

        public int[] solution(int n, int s) {
            if (s < n) {
                return new int[] { -1 };
            }

            int[] answer = new int[n];
            int remainder = s % n;
            int element = s / n;
            for (int i = 0; i < answer.length - remainder; i++) {
                answer[i] = element;
            }
            element++;
            for (int i = answer.length - remainder; i < answer.length; i++) {
                answer[i] = element;
            }
            return answer;
        }

    }

}
