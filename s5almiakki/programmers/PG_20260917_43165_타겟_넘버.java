public class PG_20260917_43165_타겟_넘버 {

	class Solution {

		int answer = 0;

		public int solution(int[] numbers, int target) {
			dfs(numbers, target, 0, 0);
			return answer;
		}

		void dfs(int[] numbers, int target, int sum, int depth) {
			if (depth == numbers.length) {
				if (sum == target) {
					answer++;
				}
				return;
			}
			dfs(numbers, target, sum + numbers[depth], depth + 1);
			dfs(numbers, target, sum - numbers[depth], depth + 1);
		}

	}

}
