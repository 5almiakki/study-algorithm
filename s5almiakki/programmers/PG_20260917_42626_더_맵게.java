import java.util.*;

public class PG_20260917_42626_더_맵게 {

	class Solution {

		public int solution(int[] scoville, int K) {
			PriorityQueue<Long> pq = new PriorityQueue<>();
			for (int i : scoville) {
				pq.add((long) i);
			}
			for (int count = 0;; count++) {
				long score = pq.remove();
				if (score >= K) {
					return count;
				} else if (pq.isEmpty()) {
					return -1;
				}
				pq.add(score + (pq.remove() << 1L));
			}
		}

	}

}
