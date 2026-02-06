import java.util.*;

public class PG_20260206_67260_카카오_인턴_동굴_탐험 {

    class Solution {

        public boolean solution(int n, int[][] path, int[][] order) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] edge : path) {
                adjList.get(edge[0]).add(edge[1]);
                adjList.get(edge[1]).add(edge[0]);
            }

            int[] keyToLockMap = new int[n];
            Arrays.fill(keyToLockMap, -1);
            boolean[] locked = new boolean[n];
            for (int[] o : order) {
                if (o[1] == 0) {
                    return false;
                }
                keyToLockMap[o[0]] = o[1];
                locked[o[1]] = true;
            }

            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[n];
            boolean[] lockedRoomTouched = new boolean[n];
            queue.add(0);
            visited[0] = true;
            if (keyToLockMap[0] != -1) {
                locked[keyToLockMap[0]] = false;
            }
            int visitedRoomCount = 0;
            do {
                Integer room = queue.remove();
                visitedRoomCount++;
                for (Integer element : adjList.get(room.intValue())) {
                    int adjRoom = element;
                    if (visited[adjRoom]) {
                        continue;
                    }
                    if (locked[adjRoom]) {
                        lockedRoomTouched[adjRoom] = true;
                        continue;
                    }

                    int lockedRoom = keyToLockMap[adjRoom];
                    if (lockedRoom != -1) {
                        locked[lockedRoom] = false;
                        if (lockedRoomTouched[lockedRoom]) {
                            queue.add(lockedRoom);
                            visited[lockedRoom] = true;
                        }
                    }

                    queue.add(element);
                    visited[adjRoom] = true;
                }
            } while (!queue.isEmpty());

            return n == visitedRoomCount;
        }

    }

}
