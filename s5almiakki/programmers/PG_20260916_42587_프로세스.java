import java.util.*;

public class PG_20260916_42587_프로세스 {

	class Solution {

		public int solution(int[] priorities, int location) {
			SortedMap<Integer, Integer> priorityToCountMap = new TreeMap<>();
			Queue<int[]> jobQueue = new ArrayDeque<>();
			for (int i = 0; i < priorities.length; i++) {
				Integer p = priorities[i];
				priorityToCountMap.put(p, priorityToCountMap.getOrDefault(p, 0) + 1);
				jobQueue.add(new int[] { i, priorities[i] });
			}
			int answer = 1;
			for (;;) {
				int[] job = jobQueue.remove();
				int highestPriority = priorityToCountMap.lastKey();
				if (job[1] != highestPriority) {
					jobQueue.add(job);
					continue;
				}
				if (job[0] == location) {
					return answer;
				}
				int count = priorityToCountMap.get(highestPriority);
				if (count == 1) {
					priorityToCountMap.remove(highestPriority);
				} else {
					priorityToCountMap.put(highestPriority, count - 1);
				}
				answer++;
			}
		}

	}

}
