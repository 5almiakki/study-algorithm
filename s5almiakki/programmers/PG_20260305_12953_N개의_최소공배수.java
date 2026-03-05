
public class PG_20260305_12953_N개의_최소공배수 {

    class Solution1 {

        public int solution(int[] arr) {
            if (arr.length == 1) {
                return arr[0];
            }

            long gcd = arr[0];
            for (int i = 1; i < arr.length; i++) {
                gcd = gcd(gcd, arr[i]);
            }

            long multipleBound = 1L;
            long initialMultiple = 0L;
            for (int e : arr) {
                multipleBound *= (long) e;
                initialMultiple = Math.max(initialMultiple, e);
            }

            for (long multiple = initialMultiple; multiple < multipleBound; multiple += gcd) {
                boolean dividable = true;
                for (int element : arr) {
                    long e = (long) element;
                    if (multiple % e != 0) {
                        dividable = false;
                        break;
                    }
                }
                if (dividable) {
                    return (int) multiple;
                }
            }
            return (int) multipleBound;
        }

        long gcd(long a, long b) {
            if (a % b == 0) {
                return b;
            }
            return gcd(b, a % b);
        }

    }

    class Solution2 {

        public int solution(int[] arr) {
            int answer = arr[0];
            for (int i = 1; i < arr.length; i++) {
                answer *= arr[i] / gcd(answer, arr[i]);
            }
            return answer;
        }

        int gcd(int a, int b) {
            int remainder = a % b;
            return (remainder == 0) ? b : gcd(b, remainder);
        }

    }

}
