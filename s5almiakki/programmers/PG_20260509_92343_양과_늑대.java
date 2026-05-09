import java.util.*;

public class PG_20260509_92343_양과_늑대 {

    class Solution {

        public int solution(int[] info, int[][] edges) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < info.length; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                adjList.get(edge[0]).add(edge[1]);
                adjList.get(edge[1]).add(edge[0]);
            }
            return dfs(info, adjList, 1, 1, 0);
        }

        int dfs(int[] info, List<List<Integer>> adjList, int visitedMask, int sheepCount, int wolfCount) {
            if (sheepCount <= wolfCount) {
                return sheepCount;
            }
            int result = sheepCount;
            for (int node = 0; node < info.length; node++) {
                int mask = 1 << node;
                if ((visitedMask & mask) == 0) {
                    continue;
                }
                // 방문한 노드의 인접 노드 중 방문 안 한 인접 노드 선택
                for (int adjNode : adjList.get(node)) {
                    mask = 1 << adjNode;
                    if ((visitedMask & mask) != 0) {
                        continue;
                    }
                    int newResult = dfs(
                            info,
                            adjList,
                            visitedMask | mask,
                            sheepCount + 1 - info[adjNode],
                            wolfCount + info[adjNode]
                            );
                    if (result < newResult) {
                        result = newResult;
                    }
                }
            }
            return result;
        }

    }

}
