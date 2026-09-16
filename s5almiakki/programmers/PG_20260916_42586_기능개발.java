import java.util.*;

public class PG_20260916_42586_기능개발 {

	class Solution {

		public int[] solution(int[] progresses, int[] speeds) {
			List<Integer> answer = new ArrayList<>();
			int lastDeployedJobIdx = -1;
			for (;;) {
				int deployedJobIdx = lastDeployedJobIdx;
				for (int jobIdx = lastDeployedJobIdx + 1; jobIdx < progresses.length; jobIdx++) {
					if (progresses[jobIdx] < 100) {
						break;
					}
					deployedJobIdx = jobIdx;
				}
				for (int jobIdx = lastDeployedJobIdx + 1; jobIdx < progresses.length; jobIdx++) {
					progresses[jobIdx] += speeds[jobIdx];
				}
				if (deployedJobIdx != lastDeployedJobIdx) {
					answer.add(deployedJobIdx - lastDeployedJobIdx);
					if (deployedJobIdx == progresses.length - 1) {
						break;
					}
					lastDeployedJobIdx = deployedJobIdx;
				}
			}
			return answer.stream().mapToInt(Integer::intValue).toArray();
		}

	}

}
