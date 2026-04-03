import java.util.*;

public class PG_20260403_42586_기능개발 {

    class Solution {

        public int[] solution(int[] progresses, int[] speeds) {
            List<Integer> answer = new ArrayList<>();
            int base = 0;
            for (;;) {
                for (int i = base; i < progresses.length; i++) {
                    progresses[i] += speeds[i];
                }
                int distributionCount = 0;
                for (int i = base; i < progresses.length; i++) {
                    if (progresses[i] < 100) {
                        break;
                    }
                    distributionCount++;
                }
                if (distributionCount == 0) {
                    continue;
                }
                answer.add(distributionCount);
                base += distributionCount;
                if (base == progresses.length) {
                    break;
                }
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }

    }

}
