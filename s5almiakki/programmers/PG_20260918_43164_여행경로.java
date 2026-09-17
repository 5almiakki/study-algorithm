import java.util.*;

public class PG_20260918_43164_여행경로 {

	class Solution {

		List<String> path;
		List<String> answer;

		public String[] solution(String[][] tickets) {
			Map<String, SortedMap<String, Integer>> adjList = new HashMap<>();
			for (String[] ticket : tickets) {
				put(adjList, ticket[0], ticket[1]);
			}

			path = new ArrayList<>(tickets.length + 1);
			dfs(adjList, new HashMap<>(), "ICN");
			return answer.toArray(new String[0]);
		}

		void dfs(
				Map<String, SortedMap<String, Integer>> adjList,
				Map<String, SortedMap<String, Integer>> history,
				String node) {
			path.add(node);
			if (adjList.equals(history)) {
				answer = new ArrayList<>(path);
				return;
			}
			Map<String, Integer> adjNodes = adjList.get(node);
			if (adjNodes != null) {
				for (Map.Entry<String, Integer> entry : adjNodes.entrySet()) {
					if (answer != null) {
						return;
					}
					String adjNode = entry.getKey();
					if (history.get(node) != null
							&& entry.getValue().equals(history.get(node).get(adjNode))) {
						continue;
					}
					put(history, node, adjNode);
					dfs(adjList, history, adjNode);
					remove(history, node, adjNode);
				}
			}
			path.remove(path.size() - 1);
		}

		void put(Map<String, SortedMap<String, Integer>> map, String key, String value) {
			SortedMap<String, Integer> innerMap = map.get(key);
			if (innerMap == null) {
				innerMap = new TreeMap<>();
				map.put(key, innerMap);
			}
			Integer count = innerMap.get(value);
			if (count == null) {
				innerMap.put(value, 1);
			} else {
				innerMap.put(value, count + 1);
			}
		}

		void remove(Map<String, SortedMap<String, Integer>> map, String key, String value) {
			SortedMap<String, Integer> innerMap = map.get(key);
			if (innerMap == null) {
				return;
			}
			Integer count = innerMap.get(value);
			if (count == null) {
				return;
			}
			if (count <= 1) {
				innerMap.remove(value);
			} else {
				innerMap.put(value, count - 1);
			}
			if (innerMap.isEmpty()) {
				map.remove(key);
			}
		}

	}

}
