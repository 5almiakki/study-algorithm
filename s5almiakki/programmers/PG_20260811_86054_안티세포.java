import java.util.*;

public class PG_20260811_86054_안티세포 {

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
			List<Map<Long, DpInfo>> dp = new ArrayList<>(cells.length);
			Map<Long, DpInfo> map = new HashMap<>();
			map.put((long) cells[0], new DpInfo(1, 0));
			dp.add(map);

			for (int i = 1; i < cells.length; i++) {
				long targetSum = cells[i];
				DpInfo dpInfo = new DpInfo(0, 0);
				for (DpInfo value : map.values()) {
					dpInfo.caseCount = (dpInfo.caseCount + value.caseCount) % DIVISOR;
				}
				dpInfo.beginIdx = i;
				map = new HashMap<>();
				map.put(targetSum, dpInfo);
				dp.add(map);

				for (;;) {
					DpInfo lastDpInfo = map.get(targetSum);
					int newBeginIdx = lastDpInfo.beginIdx - 1;
					if (newBeginIdx < 0) {
						break;
					}
					Map<Long, DpInfo> oldMap = dp.get(newBeginIdx);
					if (!oldMap.containsKey(targetSum)) {
						break;
					}
					dpInfo = oldMap.get(targetSum);
					targetSum <<= 1L;
					map.put(targetSum, new DpInfo(dpInfo));
				}
			}

			int answer = 0;
			for (DpInfo dpInfo : dp.get(dp.size() - 1).values()) {
				answer = (answer + dpInfo.caseCount) % DIVISOR;
			}
			return answer;
		}

		static class DpInfo {

			int caseCount;
			int beginIdx;

			DpInfo(int caseCount, int beginIdx) {
				this.caseCount = caseCount;
				this.beginIdx = beginIdx;
			}

			DpInfo(DpInfo other) {
				this(other.caseCount, other.beginIdx);
			}

		}

	}

}
