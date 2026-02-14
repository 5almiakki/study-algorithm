import java.util.*;

public class PG_20260214_68937_트리_트리오_중간값 {

    class Solution {

        public int solution(int n, int[][] edges) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                int node1 = edge[0] - 1;
                int node2 = edge[1] - 1;
                adjList.get(node1).add(node2);
                adjList.get(node2).add(node1);
            }

            Queue<int[]> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[n];
            int farthestNode = 0;
            int maxDistance = 0;
            queue.add(new int[] { 0, 0 }); // { node, distance }
            visited[0] = true;
            do {
                int[] moment = queue.remove();
                if (maxDistance < moment[1]) {
                    farthestNode = moment[0];
                    maxDistance = moment[1];
                }
                for (int adjNode : adjList.get(moment[0])) {
                    if (visited[adjNode]) {
                        continue;
                    }
                    queue.add(new int[] { adjNode, moment[1] + 1 });
                    visited[adjNode] = true;
                }
            } while (!queue.isEmpty());

            queue.add(new int[] { farthestNode, 0 });
            Arrays.fill(visited, false);
            visited[farthestNode] = true;
            farthestNode = -1;
            int maxDistance1 = 0;
            int maxDistance2 = 0;
            do {
                int[] moment = queue.remove();
                if (maxDistance1 <= moment[1]) {
                    maxDistance2 = maxDistance1;
                    farthestNode = moment[0];
                    maxDistance1 = moment[1];
                }
                for (int adjNode : adjList.get(moment[0])) {
                    if (visited[adjNode]) {
                        continue;
                    }
                    queue.add(new int[] { adjNode, moment[1] + 1 });
                    visited[adjNode] = true;
                }
            } while (!queue.isEmpty());
            if (maxDistance1 == maxDistance2) {
                return maxDistance1;
            }

            queue.add(new int[] { farthestNode, 0 });
            Arrays.fill(visited, false);
            visited[farthestNode] = true;
            farthestNode = -1;
            maxDistance1 = 0;
            maxDistance2 = 0;
            do {
                int[] moment = queue.remove();
                if (maxDistance1 <= moment[1]) {
                    maxDistance2 = maxDistance1;
                    farthestNode = moment[0];
                    maxDistance1 = moment[1];
                }
                for (int adjNode : adjList.get(moment[0])) {
                    if (visited[adjNode]) {
                        continue;
                    }
                    queue.add(new int[] { adjNode, moment[1] + 1 });
                    visited[adjNode] = true;
                }
            } while (!queue.isEmpty());
            return maxDistance2;
        }

    }

}
