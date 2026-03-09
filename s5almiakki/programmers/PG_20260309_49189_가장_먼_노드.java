import java.util.*;

public class PG_20260309_49189_가장_먼_노드 {

    class Solution {

        public int solution(int n, int[][] edge) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] e : edge) {
                int node1 = e[0] - 1;
                int node2 = e[1] - 1;
                adjList.get(node1).add(node2);
                adjList.get(node2).add(node1);
            }

            int maxDistance = 0;
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[n];
            int[] distanceToCountMap = new int[n];
            queue.add(new int[] { 0, 0 });
            visited[0] = true;
            distanceToCountMap[0] = 1;
            do {
                int[] moment = queue.remove();
                // System.out.println("currentNode=" + moment[0] + " distance=" + moment[1]);
                int newDistance = moment[1] + 1;
                for (int adjNode : adjList.get(moment[0])) {
                    if (visited[adjNode]) {
                        continue;
                    }
                    // System.out.println("  adjNode=" + adjNode + " newDistance=" + newDistance);
                    maxDistance = Math.max(maxDistance, newDistance);
                    queue.add(new int[] { adjNode, newDistance });
                    visited[adjNode] = true;
                    distanceToCountMap[newDistance]++;
                }
            } while (!queue.isEmpty());

            return distanceToCountMap[maxDistance];
        }

    }

}
