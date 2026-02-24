
public class PG_20260224_12945_피보나치_수 {

    class Solution {

        static final int DIVISOR = 1234567;

        public int solution(int n) {
            int[] f = new int[n + 1];
            f[1] = 1;

            for (int i = 2; i <= n; i++) {
                f[i] = (f[i - 1] + f[i - 2]) % DIVISOR;
            }

            return f[n];
        }

    }

}
