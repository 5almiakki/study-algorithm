import java.util.*;

public class PG_20260131_258711_도넛과_막대_그래프 {

    class Solution {

        static final int DONUT = 1;
        static final int BAR = 2;
        static final int EIGHT = 3;
        static final int[] CYCLE_COUNT_TO_GRAPH_TYPE_MAP = { BAR, DONUT, EIGHT };

        public int[] solution(int[][] edges) {
            Set<Integer> nodes = new HashSet<>();
            Map<Integer, Integer> nodeToIndegreeMap = new HashMap<>();
            Map<Integer, List<Integer>> adjList = new HashMap<>();
            Set<Integer> visitedNodes = new HashSet<>();
            for (int[] edge : edges) {
                Integer fromNode = edge[0];
                Integer toNode = edge[1];
                nodes.add(fromNode);
                nodes.add(toNode);
                nodeToIndegreeMap.put(toNode, nodeToIndegreeMap.getOrDefault(toNode, 0) + 1);

                List<Integer> adjNodes = adjList.get(fromNode);
                if (adjNodes == null) {
                    adjNodes = new ArrayList<>();
                    adjList.put(fromNode, adjNodes);
                }
                adjNodes.add(toNode);
            }

            int[] answer = new int[4];
            // 무관한 점
            for (Integer node : adjList.keySet()) {
                if (nodeToIndegreeMap.containsKey(node) || adjList.get(node).size() < 2) {
                    continue;
                }
                answer[0] = node;
                List<Integer> adjNodes = adjList.get(node);
                for (Integer adjNode : adjNodes) {
                    int indegree = nodeToIndegreeMap.get(adjNode);
                    if (indegree == 1) {
                        nodeToIndegreeMap.remove(adjNode);
                    } else {
                        nodeToIndegreeMap.put(adjNode, indegree - 1);
                    }
                }
                nodes.remove(node);
                adjList.remove(node);
                break;
            }

            Queue<Integer> queue = new ArrayDeque<>();
            // 막대 모양 그래프의 시작 정점에서부터 BFS
            for (Integer node : nodes) {
                if (nodeToIndegreeMap.containsKey(node) || visitedNodes.contains(node)) {
                    continue;
                }
                int cycleCount = computeCycleCount(adjList, visitedNodes, node, queue);
                int graphType = CYCLE_COUNT_TO_GRAPH_TYPE_MAP[cycleCount];
                answer[graphType]++;
            }
            // 나머지 그래프 BFS
            for (Integer node : adjList.keySet()) {
                if (visitedNodes.contains(node)) {
                    continue;
                }
                int cycleCount = computeCycleCount(adjList, visitedNodes, node, queue);
                int graphType = CYCLE_COUNT_TO_GRAPH_TYPE_MAP[cycleCount];
                answer[graphType]++;
            }

            return answer;
        }

        int computeCycleCount(
                Map<Integer, List<Integer>> adjList,
                Set<Integer> visitedNodes,
                Integer beginNode,
                Queue<Integer> queue
                ) {
            queue.add(beginNode);
            visitedNodes.add(beginNode);
            int cycleCount = 0;
            do {
                Integer node = queue.remove();
                if (!adjList.containsKey(node)) {
                    continue;
                }
                for (Integer adjNode : adjList.get(node)) {
                    if (visitedNodes.contains(adjNode)) {
                        cycleCount++;
                        continue;
                    }
                    queue.add(adjNode);
                    visitedNodes.add(adjNode);
                }
            } while (!queue.isEmpty());

            return cycleCount;
        }

    }

}
