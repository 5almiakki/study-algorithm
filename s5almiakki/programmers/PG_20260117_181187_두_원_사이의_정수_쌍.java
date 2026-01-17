
public class PG_20260117_181187_두_원_사이의_정수_쌍 {

    class Solution {

        public long solution(int r1, int r2) {
            long lr1 = r1;
            long lr2 = r2;
            long square1 = lr1 * lr1;
            long square2 = lr2 * lr2;
            long answer = 0L;
            for (long x = 1L; x < lr1; x++) {
                long squareX = x * x;
                answer += (long) Math.floor(Math.sqrt(square2 - squareX)) - (long) Math.ceil(Math.sqrt(square1 - squareX)) + 1L;
            }
            for (long x = lr1; x <= lr2; x++) {
                long squareX = x * x;
                answer += (long) Math.floor(Math.sqrt(square2 - squareX)) + 1L;
            }
            return answer << 2L;
        }

    }

    /*
    x^2 + y^2 = r^2
    y = sqrt(r^2 - x^2)
     */

}
