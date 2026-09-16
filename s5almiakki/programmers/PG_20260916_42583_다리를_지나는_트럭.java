import java.util.*;

public class PG_20260916_42583_다리를_지나는_트럭 {

	class Solution {

		public int solution(int bridgeLength, int weight, int[] truckWeights) {
			int truckIdx = 0;
			int weightSum = 0;
			// truck = { weight, time }
			Queue<int[]> bridge = new ArrayDeque<>(bridgeLength);
			for (int time = 1;; time++) {
				if (!bridge.isEmpty()) {
					int[] truck = bridge.peek();
					if (time - truck[1] >= bridgeLength) {
						bridge.remove();
						weightSum -= truck[0];
					}
				}
				if (truckIdx == truckWeights.length) {
					if (weightSum == 0) {
						return time;
					}
					continue;
				}
				int newWeightSum = weightSum + truckWeights[truckIdx];
				if (newWeightSum <= weight) {
					bridge.add(new int[] { truckWeights[truckIdx], time });
					truckIdx++;
					weightSum = newWeightSum;
				}
			}
		}

	}

}
