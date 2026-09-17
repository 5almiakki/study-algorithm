import java.util.*;

public class PG_20260917_42748_K번째수 {

	class Solution {

		public int[] solution(int[] array, int[][] commands) {
			int[] answer = new int[commands.length];
			for (int i = 0; i < commands.length; i++) {
				int from = commands[i][0] - 1;
				int to = commands[i][1] - 1;
				int targetIdx = commands[i][2] - 1;
				int[] subArray = Arrays.copyOfRange(array, from, to + 1);
				Arrays.sort(subArray);
				answer[i] = subArray[targetIdx];
			}
			return answer;
		}

	}

}
