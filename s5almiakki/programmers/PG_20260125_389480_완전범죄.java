
public class PG_20260125_389480_완전범죄 {

    class Solution1 {

        private int[][] info;
        private int limitA;
        private int limitB;

        private boolean[][][] visited = new boolean[40][120][120];

        private int answer = Integer.MAX_VALUE;

        public int solution(int[][] info, int n, int m) {
            this.info = info;
            limitA = n;
            limitB = m;
            dfs(0, 0, 0);
            return (answer == Integer.MAX_VALUE) ? -1 : answer;
        }

        private void dfs(int depth, int sumA, int sumB) {
            if (depth == info.length) {
                answer = sumA;
                return;
            }

            if (visited[depth][sumA][sumB]) {
                return;
            }
            visited[depth][sumA][sumB] = true;

            sumA += info[depth][0];
            if (sumA < limitA && sumA < answer) {
                dfs(depth + 1, sumA, sumB);
            }

            sumA -= info[depth][0];
            sumB += info[depth][1];
            if (sumB < limitB) {
                dfs(depth + 1, sumA, sumB);
            }
        }

    }

}
