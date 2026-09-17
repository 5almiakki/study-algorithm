import java.util.*;

public class PG_20260917_43162_네트워크 {

	class Solution {

		public int solution(int n, int[][] computers) {
			boolean[] visited = new boolean[n];
			int answer = 0;
			for (int beginNode = 0; beginNode < n; beginNode++) {
				if (visited[beginNode]) {
					continue;
				}
				bfs(computers, visited, beginNode);
				answer++;
			}
			return answer;
		}

		void bfs(int[][] computers, boolean[] visited, int beginNode) {
			Queue<Integer> queue = new ArrayDeque<>();
			queue.add(beginNode);
			visited[beginNode] = true;
			do {
				int node = queue.remove();
				for (int adjNode = 0; adjNode < computers[node].length; adjNode++) {
					if (visited[adjNode] || computers[node][adjNode] == 0) {
						continue;
					}
					queue.add(adjNode);
					visited[adjNode] = true;
				}
			} while (!queue.isEmpty());
		}

	}

}
