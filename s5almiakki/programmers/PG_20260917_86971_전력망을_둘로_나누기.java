import java.util.*;

public class PG_20260917_86971_전력망을_둘로_나누기 {

	class Solution {

		public int solution(int n, int[][] wires) {
			List<Set<Integer>> adjList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				adjList.add(new HashSet<>());
			}
			for (int[] wire : wires) {
				wire[0]--;
				wire[1]--;
				adjList.get(wire[0]).add(wire[1]);
				adjList.get(wire[1]).add(wire[0]);
			}
			int answer = Integer.MAX_VALUE;
			for (int[] cutWire : wires) {
				adjList.get(cutWire[0]).remove(cutWire[1]);
				adjList.get(cutWire[1]).remove(cutWire[0]);
				int nodeCount = bfs(adjList);
				answer = Math.min(answer, Math.abs((nodeCount << 1) - n));
				adjList.get(cutWire[0]).add(cutWire[1]);
				adjList.get(cutWire[1]).add(cutWire[0]);
			}
			return answer;
		}

		int bfs(List<Set<Integer>> adjList) {
			Queue<Integer> queue = new ArrayDeque<>();
			boolean[] visited = new boolean[adjList.size()];
			queue.add(0);
			visited[0] = true;
			int nodeCount = 1;
			do {
				int node = queue.remove();
				for (Integer e : adjList.get(node)) {
					int adjNode = e;
					if (visited[adjNode]) {
						continue;
					}
					queue.add(e);
					visited[adjNode] = true;
					nodeCount++;
				}
			} while (!queue.isEmpty());
			return nodeCount;
		}

	}

}
