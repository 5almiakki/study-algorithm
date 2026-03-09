import java.util.*;

public class PG_20260309_42861_섬_연결하기 {

    class Solution {

        public int solution(int n, int[][] costs) {
            List<List<int[]>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] cost : costs) {
                adjList.get(cost[0]).add(new int[] { cost[1], cost[2] });
                adjList.get(cost[1]).add(new int[] { cost[0], cost[2] });
            }

            boolean[] visited = new boolean[n];
            visited[0] = true;
            int visitedNodeCount = 1;
            int answer = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
            for (int[] adjNode : adjList.get(0)) {
                pq.add(adjNode);
            }

            while (visitedNodeCount < n) {
                int[] node = pq.remove();
                if (visited[node[0]]) {
                    continue;
                }
                visited[node[0]] = true;
                visitedNodeCount++;
                answer += node[1];
                for (int[] adjNode : adjList.get(node[0])) {
                    pq.add(adjNode);
                }
            }

            return answer;
        }

    }

}
