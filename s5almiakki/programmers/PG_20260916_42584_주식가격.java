import java.util.*;

public class PG_20260916_42584_주식가격 {

	class Solution {

		public int[] solution(int[] prices) {
			int[] answer = new int[prices.length];
			Deque<Integer> stack = new ArrayDeque<>();
			for (int i = 0; i < prices.length; i++) {
				for (;;) {
					Integer idx = stack.peek();
					if (idx == null) {
						stack.push(i);
						break;
					}
					int v = idx;
					if (prices[idx] <= prices[i]) {
						stack.push(i);
						break;
					}
					stack.pop();
					answer[v] = i - v;
				}
			}
			while (!stack.isEmpty()) {
				int idx = stack.pop();
				answer[idx] = prices.length - 1 - idx;
			}
			return answer;
		}

	}

}
