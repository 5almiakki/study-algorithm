
public class PG_20260118_12923_숫자_블록 {

    class Solution {

        public int[] solution(long begin, long end) {
            int[] answer = new int[(int) (end - begin) + 1];
            int beginI = 0;
            if (begin == 1) {
                answer[0] = 0;
                beginI = 1;
            }
            for (int i = beginI; i < answer.length; i++) {
                int cell = (int) begin + i;

                int endDivisor = (int) Math.sqrt(cell);
                int maxBlock = 1;
                for (int divisor = 2; divisor <= endDivisor; divisor++) {
                    if (cell % divisor != 0) {
                        continue;
                    }
                    int block = cell / divisor;
                    if (block <= 10_000_000) {
                        maxBlock = Math.max(maxBlock, block);
                    }
                    if (divisor <= 10_000_000) {
                        maxBlock = Math.max(maxBlock, divisor);
                    }
                }
                answer[i] = maxBlock;
            }
            return answer;
        }

    }

}
