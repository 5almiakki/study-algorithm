public class PG_20260917_87946_피로도 {

	class Solution {

		int answer = 0;

		public int solution(int k, int[][] dungeons) {
			dfs(dungeons, 0, k);
			return answer;
		}

		void dfs(int[][] dungeons, int chosenMask, int fatigue) {
			answer = Math.max(answer, Integer.bitCount(chosenMask));
			for (int i = 0; i < dungeons.length; i++) {
				int mask = 1 << i;
				if (fatigue < dungeons[i][0] || (chosenMask & mask) != 0) {
					continue;
				}
				dfs(dungeons, chosenMask | mask, fatigue - dungeons[i][1]);
			}
		}

	}

}
