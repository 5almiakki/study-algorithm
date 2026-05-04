import java.util.*;

public class PG_20260504_72413_합승_택시_요금 {

    class Solution {

        public int solution(int n, int s, int a, int b, int[][] fares) {
            s--;
            a--;
            b--;
            List<List<int[]>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] fare : fares) {
                int node1 = fare[0] - 1;
                int node2 = fare[1] - 1;
                adjList.get(node1).add(new int[] { node2, fare[2] });
                adjList.get(node2).add(new int[] { node1, fare[2] });
            }

            Queue<int[]> queue = new ArrayDeque<>();
            int[] distancesFromS = bfs(adjList, queue, s);
            int[] distancesFromA = bfs(adjList, queue, a);
            int[] distancesFromB = bfs(adjList, queue, b);
            int answer = Integer.MAX_VALUE;
            for (int transit = 0; transit < n; transit++) {
                int sum = distancesFromS[transit] + distancesFromA[transit] + distancesFromB[transit];
                if (sum < answer) {
                    answer = sum;
                }
            }
            return answer;
        }

        int[] bfs(List<List<int[]>> adjList, Queue<int[]> queue, int beginNode) {
            int[] distances = new int[adjList.size()];
            Arrays.fill(distances, Integer.MAX_VALUE);
            queue.add(new int[] { beginNode, 0 });
            distances[beginNode] = 0;
            do {
                int[] moment = queue.remove();
                for (int[] adjNode : adjList.get(moment[0])) {
                    int newDistance = moment[1] + adjNode[1];
                    if (distances[adjNode[0]] <= newDistance) {
                        continue;
                    }
                    queue.add(new int[] { adjNode[0], newDistance });
                    distances[adjNode[0]] = newDistance;
                }
            } while (!queue.isEmpty());
            return distances;
        }

    }

}
