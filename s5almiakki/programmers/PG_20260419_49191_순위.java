import java.util.*;

public class PG_20260419_49191_순위 {

    class Solution {

        public int solution(int n, int[][] results) {
            List<List<Integer>> adjList1 = new ArrayList<>();
            List<List<Integer>> adjList2 = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList1.add(new ArrayList<>());
                adjList2.add(new ArrayList<>());
            }
            for (int[] result : results) {
                int node1 = result[0] - 1;
                int node2 = result[1] - 1;
                adjList1.get(node1).add(node2);
                adjList2.get(node2).add(node1);
            }

            Queue<Integer> queue = new ArrayDeque<>();
            int targetCount = n + 1;
            int answer = 0;
            for (int i = 0; i < n; i++) {
                // System.out.println("beginNode: " + i
                //         + ", bfs1: " + bfs(n, adjList1, i, queue)
                //         + ", bfs2: " + bfs(n, adjList2, i, queue));
                if (bfs(n, adjList1, i, queue) + bfs(n, adjList2, i, queue) == targetCount) {
                    answer++;
                }
            }
            return answer;
        }

        int bfs(int nodeCount, List<List<Integer>> adjList, int beginNode, Queue<Integer> queue) {
            boolean[] visited = new boolean[nodeCount];
            queue.add(beginNode);
            visited[beginNode] = true;
            int visitedNodeCount = 0;
            do {
                int node = queue.remove();
                // System.out.println(node);
                visitedNodeCount++;
                for (Integer adjNode : adjList.get(node)) {
                    int i = adjNode;
                    if (visited[i]) {
                        continue;
                    }
                    queue.add(adjNode);
                    visited[i] = true;
                }
            } while (!queue.isEmpty());
            return visitedNodeCount;
        }

    }

}
