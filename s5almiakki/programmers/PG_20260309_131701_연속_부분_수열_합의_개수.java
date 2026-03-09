import java.util.*;

public class PG_20260309_131701_연속_부분_수열_합의_개수 {

    class Solution {

        public int solution(int[] elements) {
            int sum = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            for (int element : elements) {
                sum += element;
                queue.add(element);
            }

            boolean[] visited = new boolean[sum + 1];
            visited[sum] = true;
            int answer = 1;

            for (int i = 0; i < elements.length; i++) {
                sum = 0;
                for (int element : queue) {
                    sum += element;
                    if (visited[sum]) {
                        continue;
                    }
                    answer++;
                    visited[sum] = true;
                }
                queue.add(queue.remove());
            }

            return answer;
        }

    }

}
