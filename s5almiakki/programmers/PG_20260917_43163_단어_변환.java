import java.util.*;

public class PG_20260917_43163_단어_변환 {

	class Solution {

		public int solution(String begin, String target, String[] words) {
			Map<String, List<String>> adjList = new HashMap<>();
			adjList.put(begin, computeAdjNodes(begin, words));
			for (String key : words) {
				adjList.put(key, computeAdjNodes(key, words));
			}

			Queue<Moment> queue = new ArrayDeque<>();
			Set<String> visitedWords = new HashSet<>();
			queue.add(new Moment(begin, 0));
			visitedWords.add(begin);
			do {
				Moment m = queue.remove();
				int newCount = m.count + 1;
				for (String adjNode : adjList.get(m.node)) {
					if (visitedWords.contains(adjNode)) {
						continue;
					}
					if (adjNode.equals(target)) {
						return newCount;
					}
					queue.add(new Moment(adjNode, newCount));
					visitedWords.add(adjNode);
				}
			} while (!queue.isEmpty());
			return 0;
		}

		List<String> computeAdjNodes(String key, String[] words) {
			List<String> adjNodes = new ArrayList<>();
			for (String word : words) {
				if (isAdjacent(key, word)) {
					adjNodes.add(word);
				}
			}
			return adjNodes;
		}

		boolean isAdjacent(String word1, String word2) {
			int diffCount = 0;
			for (int i = word1.length() - 1; i >= 0; i--) {
				if (word1.charAt(i) == word2.charAt(i)) {
					continue;
				}
				diffCount++;
				if (diffCount > 1) {
					return false;
				}
			}
			return diffCount == 1;
		}

		static class Moment {

			String node;
			int count;

			Moment(String node, int count) {
				this.node = node;
				this.count = count;
			}

		}

	}

}
