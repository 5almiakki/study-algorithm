import java.util.*;

public class PG_20260208_62050_지형_이동 {

    class Solution {

        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        int size;
        int[][] groupIds;
        int groupCount;

        public int solution(int[][] land, int height) {
            size = land.length;
            groupIds = new int[land.length][land.length];
            for (int[] row : groupIds) {
                Arrays.fill(row, -1);
            }

            setGroupIds(land, height);

            Map<Integer, Map<Integer, Integer>> adjList = makeAdjList(land);

            return computeMinSpanTreeCost(adjList);
        }

        boolean isOutOfBounds(int row, int col) {
            return row < 0 || size <= row
                    || col < 0 || size <= col;
        }

        void setGroupIds(int[][] land, int height) {
            int groupId = 0;
            Queue<int[]> queue = new ArrayDeque<>();
            for (int row = 0; row < land.length; row++) {
                for (int col = 0; col < land[row].length; col++) {
                    if (groupIds[row][col] == -1) {
                        setGroupIds(land, height, groupId, queue, row, col);
                        groupId++;
                    }
                }
            }
            groupCount = groupId;
        }

        void setGroupIds(
                int[][] land, int height, int groupId, Queue<int[]> queue,
                int beginRow, int beginCol
                ) {
            groupIds[beginRow][beginCol] = groupId;
            queue.add(new int[] { beginRow, beginCol });
            do {
                int[] p = queue.remove();
                for (int[] delta : DELTAS) {
                    int newRow = p[0] + delta[0];
                    int newCol = p[1] + delta[1];
                    if (isOutOfBounds(newRow, newCol) || groupIds[newRow][newCol] != -1) {
                        continue;
                    }
                    int heightDifference = Math.abs(land[p[0]][p[1]] - land[newRow][newCol]);
                    if (heightDifference > height) {
                        continue;
                    }
                    groupIds[newRow][newCol] = groupId;
                    queue.add(new int[] { newRow, newCol });
                }
            } while (!queue.isEmpty());
        }

        Map<Integer, Map<Integer, Integer>> makeAdjList(int[][] land) {
            Map<Integer, Map<Integer, Integer>> adjList = new HashMap<>();
            for (int i = 0; i < groupCount; i++) {
                adjList.put(i, new HashMap<>());
            }
            for (int row = 0; row < land.length; row++) {
                for (int col = 0; col < land[row].length; col++) {
                    int groupId = groupIds[row][col];
                    for (int[] delta : DELTAS) {
                        int adjRow = row + delta[0];
                        int adjCol = col + delta[1];
                        if (isOutOfBounds(adjRow, adjCol)) {
                            continue;
                        }
                        int adjGroupId = groupIds[adjRow][adjCol];
                        if (groupId == adjGroupId) {
                            continue;
                        }
                        int newCost = Math.abs(land[row][col] - land[adjRow][adjCol]);
                        Integer k1 = groupId;
                        Integer k2 = adjGroupId;
                        Integer v = newCost;
                        Integer cost = adjList.get(k1).get(k2);
                        if (cost == null || newCost < cost) {
                            adjList.get(k1).put(k2, v);
                            adjList.get(k2).put(k1, v);
                        }
                    }
                }
            }
            return adjList;
        }

        int computeMinSpanTreeCost(Map<Integer, Map<Integer, Integer>> adjList) {
            int result = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
            boolean[] visited = new boolean[groupCount];
            for (Map.Entry<Integer, Integer> e : adjList.get(0).entrySet()) {
                pq.add(new int[] { e.getKey(), e.getValue() });
            }
            visited[0] = true;
            while (!pq.isEmpty()) {
                int[] e = pq.remove();
                if (visited[e[0]]) {
                    continue;
                }
                visited[e[0]] = true;
                result += e[1];
                for (Map.Entry<Integer, Integer> entry : adjList.get(e[0]).entrySet()) {
                    int n = entry.getKey();
                    if (!visited[n]) {
                        pq.add(new int[] { n, entry.getValue() });
                    }
                }
            }
            return result;
        }

    }

}
