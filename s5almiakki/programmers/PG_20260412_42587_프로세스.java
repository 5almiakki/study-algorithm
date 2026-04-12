import java.util.*;

public class PG_20260412_42587_프로세스 {

    class Solution {

        public int solution(int[] priorities, int location) {
            SortedMap<Integer, Integer> priorityToCountMap = new TreeMap<>();
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < priorities.length; i++) {
                Integer priority = priorities[i];
                priorityToCountMap.put(priority, priorityToCountMap.getOrDefault(priority, 0) + 1);
                queue.add(new int[] { i, priorities[i] });
            }

            int answer = 0;
            for (;;) {
                int[] process = queue.remove();
                if (process[1] != priorityToCountMap.lastKey()) {
                    queue.add(process);
                    continue;
                }
                answer++;
                if (process[0] == location) {
                    break;
                }
                Integer priority = process[1];
                int count = priorityToCountMap.get(priority);
                if (count == 1) {
                    priorityToCountMap.remove(priority);
                } else {
                    priorityToCountMap.put(priority, count - 1);
                }
            }
            return answer;
        }

    }

}
