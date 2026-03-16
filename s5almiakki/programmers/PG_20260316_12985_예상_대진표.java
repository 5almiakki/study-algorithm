
public class PG_20260316_12985_예상_대진표 {

    class Solution {

        public int solution(int n, int a, int b) {
            a--;
            b--;
            if (b < a) {
                int t = b;
                b = a;
                a = t;
            }

            int answer = 1;
            for (;;) {
                int newA = a >> 1;
                int newB = b >> 1;
                if (newA == newB && b - a == 1) {
                    break;
                }
                a = newA;
                b = newB;
                answer++;
            }

            return answer;
        }

    }

}
