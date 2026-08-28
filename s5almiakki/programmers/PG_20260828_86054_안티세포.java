import java.util.*;

public class PG_20260828_86054_안티세포 {

	class Solution {

		static final int DIVISOR = 1_000_000_007;

		public int[] solution(int[] a, int[] s) {
			int[] answer = new int[s.length];
			int from = 0;
			for (int i = 0; i < s.length; i++) {
				int to = from + s[i];
				answer[i] = solve(Arrays.copyOfRange(a, from, to));
				from = to;
			}
			return answer;
		}

		int solve(int[] cells) {
			List<Map<Long, DpInfo>> dp = new ArrayList<>();
			Map<Long, DpInfo> map = new HashMap<>();
			map.put((long) cells[0], new DpInfo(0, 1));
			dp.add(map);
			for (int i = 1; i < cells.length; i++) {
				int caseCount = 0;
				for (DpInfo di : map.values()) {
					caseCount = (caseCount + di.caseCount) % DIVISOR;
				}
				map = new HashMap<>();
				long targetSum = cells[i];
				map.put(targetSum, new DpInfo(i, caseCount));
				dp.add(map);

				for (;;) {
					DpInfo di = map.get(targetSum);
					int prevHead = di.tail - 1;
					if (prevHead < 0) {
						break;
					}
					Map<Long, DpInfo> prevMap = dp.get(prevHead);
					DpInfo prevDi = prevMap.get(targetSum);
					if (prevDi == null) {
						break;
					}
					targetSum <<= 1L;
					map.put(targetSum, new DpInfo(prevDi));
				}
			}
			int answer = 0;
			for (DpInfo di : dp.get(dp.size() - 1).values()) {
				answer = (answer + di.caseCount) % DIVISOR;
			}
			return answer;
		}

		static class DpInfo {

			int tail;
			int caseCount;

			DpInfo(int tail, int caseCount) {
				this.tail = tail;
				this.caseCount = caseCount;
			}

			DpInfo(DpInfo other) {
				this(other.tail, other.caseCount);
			}

		}

	}

}
