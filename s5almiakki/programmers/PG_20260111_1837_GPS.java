import java.util.*;

public class PG_20260111_1837_GPS {

    class Solution {

        // private StringBuilder log = new StringBuilder();
        // private String ln = System.lineSeparator();

        public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
            int[] gpsLog = Arrays.copyOf(gps_log, k);
            for (int i = 0; i < k; i++) {
                gpsLog[i]--;
            }
            // log.append(Arrays.toString(gpsLog)).append(ln);

            List<Set<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Set<Integer> adjNodes = new HashSet<>();
                adjNodes.add(i);
                adjList.add(adjNodes);
            }
            for (int[] edge : edge_list) {
                int node1 = edge[0] - 1;
                int node2 = edge[1] - 1;
                adjList.get(node1).add(node2);
                adjList.get(node2).add(node1);
            }
            // for (int i = 0; i < adjList.size(); i++) {
            //     log.append(i).append(':').append(adjList.get(i)).append(ln);
            // }

            Map<Integer, Integer> nodeToChangeCountMap = null;
            Map<Integer, Integer> nextNodeToChangeCountMap = new HashMap<>();
            nextNodeToChangeCountMap.put(gpsLog[0], 0);

            for (int i = 1; i < gpsLog.length; i++) {
                nodeToChangeCountMap = nextNodeToChangeCountMap;
                // log.append(nodeToChangeCountMap).append(ln);

                nextNodeToChangeCountMap = new HashMap<>();
                Integer nextNode = gpsLog[i];

                for (Map.Entry<Integer, Integer> e : nodeToChangeCountMap.entrySet()) {
                    for (Integer adjNode : adjList.get(e.getKey())) {
                        Integer changeCount = nextNodeToChangeCountMap.get(adjNode);
                        int newChangeCount = nextNode.equals(adjNode) ? e.getValue() : e.getValue() + 1;
                        if (changeCount == null || changeCount.compareTo(newChangeCount) > 0) {
                            nextNodeToChangeCountMap.put(adjNode, newChangeCount);
                        }
                    }
                }
            }

            // System.out.print(log);
            Integer answer = nextNodeToChangeCountMap.get(gpsLog[k - 1]);
            return answer == null ? -1 : answer;
        }

    }

}
