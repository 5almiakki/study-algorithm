import java.util.*;

public class PG_20260126_43162_네트워크 {

    class Solution {

        public int solution(int n, int[][] computers) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < computers.length; i++) {
                List<Integer> adjNodes = new ArrayList<>();
                for (int j = 0; j < computers[i].length; j++) {
                    if (computers[i][j] == 1) {
                        adjNodes.add(j);
                    }
                }
                adjList.add(adjNodes);
            }

            int answer = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            Set<Integer> visitedNodes = new HashSet<>();
            for (int beginNode = 0; beginNode < n; beginNode++) {
                Integer element = beginNode;
                if (visitedNodes.contains(element)) {
                    continue;
                }
                bfs(queue, adjList, visitedNodes, element);
                answer++;
            }
            return answer;
        }

        private void bfs(Queue<Integer> queue, List<List<Integer>> adjList, Set<Integer> visitedNodes, Integer beginNode) {
            queue.add(beginNode);
            visitedNodes.add(beginNode);
            do {
                int node = queue.remove();
                for (Integer adjNode : adjList.get(node)) {
                    if (!visitedNodes.contains(adjNode)) {
                        queue.add(adjNode);
                        visitedNodes.add(adjNode);
                    }
                }
            } while (!queue.isEmpty());
        }

    }

}
