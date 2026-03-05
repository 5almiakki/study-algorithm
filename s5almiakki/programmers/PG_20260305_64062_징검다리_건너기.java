
public class PG_20260305_64062_징검다리_건너기 {

    class Solution {

        public int solution(int[] stones, int k) {
            int[] stonesCopy = new int[stones.length];
            int high = 200_000_000;
            int low = 1;
            int answer = 0;
            do {
                int mid = (high + low) >> 1;
                for (int i = 0; i < stonesCopy.length; i++) {
                    stonesCopy[i] = stones[i] - mid;
                }

                int successive0Count = 0;
                for (int stone : stonesCopy) {
                    if (stone > 0) {
                        successive0Count = 0;
                        continue;
                    }
                    successive0Count++;
                    if (successive0Count == k) {
                        break;
                    }
                }

                if (successive0Count < k) {
                    answer = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } while (low <= high);
            return answer + 1;
        }

    }

}
