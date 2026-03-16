import java.util.*;

public class PG_20260316_132266_부대복귀 {

    class Solution {

        public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] road : roads) {
                int node1 = road[0] - 1;
                int node2 = road[1] - 1;
                adjList.get(node1).add(node2);
                adjList.get(node2).add(node1);
            }

            destination--;
            Queue<int[]> queue = new ArrayDeque<>();
            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            queue.add(new int[] { destination, 0 });
            distances[destination] = 0;
            do {
                int[] moment = queue.remove();
                for (int adjNode : adjList.get(moment[0])) {
                    int newDistance = moment[1] + 1;
                    if (distances[adjNode] < newDistance) {
                        continue;
                    }
                    queue.add(new int[] { adjNode, newDistance });
                    distances[adjNode] = newDistance;
                }
            } while (!queue.isEmpty());
            // System.out.println(Arrays.toString(distances));

            int[] answer = new int[sources.length];
            for (int i = 0; i < sources.length; i++) {
                int distance = distances[sources[i] - 1];
                answer[i] = distance == Integer.MAX_VALUE ? -1 : distance;
            }
            return answer;
        }

    }

}
