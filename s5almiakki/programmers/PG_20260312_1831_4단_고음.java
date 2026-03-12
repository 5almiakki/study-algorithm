
public class PG_20260312_1831_4단_고음 {

    class Solution {

        // static final String LN = System.lineSeparator();

        // StringBuilder log = new StringBuilder();

        public int solution(int n) {
            // n = 99;
            // log.append("n=").append(n).append(LN);

            int min = computeMinCount(n);
            int max = computeMaxCount(n);
            int answer = 0;
            for (int i = min; i <= max; i++) {
                answer += dfs(0, n - 2, i, (i << 1) - 2);
            }

            // System.out.print(log);
            return answer;
        }

        // 항상 3단 고음의 1단계 직후 3단 고음을 할 때 필요한 3단 고음 개수
        int computeMaxCount(int n) {
            long mul = 1L;
            long add = 0L;
            int count = 0;
            do {
                count++;
                mul *= 3L;
                add += 2L;
            } while (mul + add < (long) n);
            return count;
        }

        // 항상 3단 고음 끝난 뒤 3단 고음을 할 때 필요한 3단 고음 개수
        int computeMinCount(int n) {
            long num = 1L;
            int count = 0;
            do {
                count++;
                num = num * 3L + 2L;
            } while (num < n);
            return count;
        }

        int dfs(int depth, int n, int mulCount, int addCount) {
            // for (int i = 0; i < depth; i++) {
            //     log.append("  ");
            // }
            // log.append("n=").append(n)
            //     .append(" mulCount=").append(mulCount)
            //     .append(" addCount=").append(addCount).append(LN);

            if ((mulCount << 1) < addCount) {
                return 0;
            }
            if (n < 1) {
                return 0;
            }
            if (n == 1) {
                if (mulCount == 0 && addCount == 0) {
                    return 1;
                }
                return 0;
            }

            int count = 0;
            int maxSubCount = Math.min(n - 1, addCount);
            for (int subCount = 0; subCount <= maxSubCount; subCount++) {
                if (n % 3 == 0) {
                    count += dfs(depth + 1, n / 3, mulCount - 1, addCount - subCount);
                }
                n--;
            }
            return count;
        }

    }

}
