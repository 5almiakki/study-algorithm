public class PG_20260917_84512_모음사전 {

	class Solution {

		char[] vowels = { 'A', 'E', 'I', 'O', 'U' };
		int answer = 0;
		boolean found = false;

		public int solution(String word) {
			dfs(word, new StringBuilder());
			return answer;
		}

		void dfs(String word, StringBuilder sb) {
			if (found) {
				return;
			}
			if (word.contentEquals(sb)) {
				found = true;
				return;
			}
			answer++;
			if (sb.length() == 5) {
				return;
			}
			for (char vowel : vowels) {
				if (found) {
					break;
				}
				sb.append(vowel);
				dfs(word, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}

	}

}
