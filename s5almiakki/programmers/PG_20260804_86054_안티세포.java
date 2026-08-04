import java.util.*;

public class PG_20260804_86054_안티세포 {

	class Solution {

		static final int DIVISOR = 1_000_000_007;

		public int[] solution(int[] a, int[] s) {
			int[] answer = new int[s.length];
			int from = 0;
			for (int i = 0; i < s.length; i++) {
				int to = from + s[i];
				int[] b = Arrays.copyOfRange(a, from, to);
				answer[i] = computeAnswer(b);
				from = to;
			}
			return answer;
		}

		int computeAnswer(int[] b) {
			// dp.get(i): i번째 안티세포까지 봤을 때
			// 그곳의 안티세포 내 수의 합이 key가 되게 하는 경우의 수는 value[0],
			// 그리고 이 안티세포의 제일 왼쪽(tail)의 위치는 value[1]
			List<Map<Long, int[]>> dp = new ArrayList<>();
			Map<Long, int[]> map = new HashMap<>();
			map.put((long) b[0], new int[] { 1, 0 }); // { count, tail }
			dp.add(map);

			for (int i = 1; i < b.length; i++) {
				long targetSum = b[i];
				int[] count = new int[2];
				for (int[] value : dp.get(i - 1).values()) {
					count[0] = (count[0] + value[0]) % DIVISOR;
				}
				count[1] = i;
				map = new HashMap<>();
				map.put(targetSum, count);
				dp.add(map);

				for (;;) {
					int[] lastCount = map.get(targetSum);
					int newTail = lastCount[1] - 1;
					if (newTail < 0) {
						break;
					}
					Map<Long, int[]> prevMap = dp.get(newTail);
					if (!prevMap.containsKey(targetSum)) {
						break;
					}
					count = prevMap.get(targetSum);
					targetSum <<= 1L;
					map.put(targetSum, new int[] { count[0], count[1] });
				}
			}

			int answer = 0;
			for (int[] count : dp.get(dp.size() - 1).values()) {
				answer = (answer + count[0]) % DIVISOR;
			}
			return answer;
		}

	}

}
