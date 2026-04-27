
public class PG_20260427_43165_타겟_넘버 {

    class Solution {

        int answer = 0;

        public int solution(int[] numbers, int target) {
            dfs(numbers, target, 0, 0);
            return answer;
        }

        void dfs(int[] numbers, int target, int depth, int result) {
            if (depth == numbers.length) {
                if (target == result) {
                    answer++;
                }
                return;
            }
            dfs(numbers, target, depth + 1, result - numbers[depth]);
            dfs(numbers, target, depth + 1, result + numbers[depth]);
        }

    }

}
