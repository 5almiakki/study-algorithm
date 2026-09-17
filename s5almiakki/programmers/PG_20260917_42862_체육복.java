import java.util.*;

public class PG_20260917_42862_체육복 {

	class Solution {

		public int solution(int n, int[] lost, int[] reserve) {
			int[] counts = new int[n];
			Arrays.fill(counts, 1);
			for (int i : lost) {
				counts[i - 1]--;
			}
			for (int i : reserve) {
				counts[i - 1]++;
			}
			if (counts[0] == 0 && counts[1] == 2) {
				counts[0] = 1;
				counts[1] = 1;
			}
			for (int i = 1; i < n - 1; i++) {
				if (counts[i] > 0) {
					continue;
				}
				if (counts[i - 1] == 2) {
					counts[i - 1] = 1;
					counts[i] = 1;
				} else if (counts[i + 1] == 2) {
					counts[i + 1] = 1;
					counts[i] = 1;
				}
			}
			if (counts[n - 2] == 2 && counts[n - 1] == 0) {
				counts[n - 2] = 1;
				counts[n - 1] = 1;
			}
			int answer = 0;
			for (int c : counts) {
				if (c >= 1) {
					answer++;
				}
			}
			return answer;
		}

	}

}
