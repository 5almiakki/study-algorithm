import java.util.*;

public class PG_20260104_홀짝트리 {

    class Solution {

        private static final int NORMAL = 0b1;
        private static final int INVERSE = 0b10;

        public int[] solution(int[] nodes, int[][] edges) {
            Map<Integer, List<Integer>> adjList = new HashMap<>();
            for (int node : nodes) {
                adjList.put(node, new ArrayList<>());
            }
            for (int[] edge : edges) {
                Integer node1 = edge[0];
                Integer node2 = edge[1];
                adjList.get(node1).add(node2);
                adjList.get(node2).add(node1);
            }

            int[] answer = new int[2];
            Set<Integer> visitedNodes = new HashSet<>();
            for (Integer node : adjList.keySet()) {
                if (visitedNodes.contains(node)) {
                    continue;
                }
                int treeType = computeTreeType(node, adjList, visitedNodes);
                if ((treeType & NORMAL) != 0) {
                    answer[0]++;
                }
                if ((treeType & INVERSE) != 0) {
                    answer[1]++;
                }
            }

            return answer;
        }

        private int computeTreeType(
                Integer beginNode,
                Map<Integer, List<Integer>> adjList,
                Set<Integer> visitedNodes) {
            Queue<Integer> queue = new ArrayDeque<>();
            visitedNodes.add(beginNode);
            queue.add(beginNode);
            int normalNodeCount = 0;
            int inverseNodeCount = 0;
            do {
                Integer node = queue.remove();
                if ((node & 1) == (adjList.get(node).size() & 1)) {
                    normalNodeCount++;
                } else {
                    inverseNodeCount++;
                }
                for (Integer adjNode : adjList.get(node)) {
                    if (visitedNodes.contains(adjNode)) {
                        continue;
                    }
                    queue.add(adjNode);
                    visitedNodes.add(adjNode);
                }
            } while (!queue.isEmpty());

            int type = 0;
            if (normalNodeCount == 1) {
                type |= NORMAL;
            }
            if (inverseNodeCount == 1) {
                type |= INVERSE;
            }
            return type;
        }

    }

}
