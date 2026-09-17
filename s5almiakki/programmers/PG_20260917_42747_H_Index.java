public class PG_20260917_42747_H_Index {

	class Solution {

		public int solution(int[] citations) {
			int[] acc = new int[10_001];
			for (int c : citations) {
				acc[c]++;
			}
			for (int i = 10_000; i >= 1; i--) {
				acc[i - 1] += acc[i];
			}
			for (int i = 10_000; i >= 0; i--) {
				if (acc[i] >= i) {
					return i;
				}
			}
			return 0;
		}

	}

}
