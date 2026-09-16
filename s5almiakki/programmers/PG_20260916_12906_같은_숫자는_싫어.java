import java.util.*;

public class PG_20260916_12906_같은_숫자는_싫어 {

	public class Solution {

		public int[] solution(int []arr) {
			Deque<Integer> stack = new ArrayDeque<>();
			for (int i : arr) {
				if (stack.isEmpty() || i != stack.peek()) {
					stack.push(i);
				}
			}
			int[] answer = new int[stack.size()];
			for (int i = 0; i < answer.length; i++) {
				answer[i] = stack.removeLast();
			}
			return answer;
		}

	}

}
