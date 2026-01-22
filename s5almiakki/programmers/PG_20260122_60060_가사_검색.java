import java.util.*;

public class PG_20260122_60060_가사_검색 {

    class Solution {

        // private static final String LN = System.lineSeparator();
        // private StringBuilder log = new StringBuilder();

        public int[] solution(String[] words, String[] queries) {
            Map<Integer, Map<Character, Node>> lengthToTrieMap = new TreeMap<>();
            Map<Integer, Map<Character, Node>> reverseLengthToTrieMap = new TreeMap<>();
            Map<Integer, Integer> lengthToCountMap = new TreeMap<>();
            for (String word : words) {
                int length = word.length();
                lengthToCountMap.put(length, lengthToCountMap.getOrDefault(length, 0) + 1);
                Node n = null;
                Node rn = null;
                Map<Character, Node> nodes = lengthToTrieMap.get(length);
                Map<Character, Node> reverseNodes = reverseLengthToTrieMap.get(length);
                if (nodes == null) {
                    nodes = new TreeMap<>();
                    lengthToTrieMap.put(length, nodes);
                    reverseNodes = new TreeMap<>();
                    reverseLengthToTrieMap.put(length, reverseNodes);
                }
                for (int i = 0; i < length; i++) {
                    Character letter = word.charAt(i);
                    if (nodes == null) {
                        nodes = new TreeMap<>();
                        n.children = nodes;
                    }
                    n = nodes.get(letter);
                    if (n == null) {
                        n = new Node();
                        nodes.put(letter, n);
                    }
                    n.count++;
                    nodes = n.children;

                    // reverse
                    letter = word.charAt(length - 1 - i);
                    if (reverseNodes == null) {
                        reverseNodes = new TreeMap<>();
                        rn.children = reverseNodes;
                    }
                    rn = reverseNodes.get(letter);
                    if (rn == null) {
                        rn = new Node();
                        reverseNodes.put(letter, rn);
                    }
                    rn.count++;
                    reverseNodes = rn.children;
                }
            }

            // logTrie(root, 0);
            // log.append(LN);

            int[] answer = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                String query = queries[i];
                // log.append(query).append(LN);
                if (query.endsWith("?")) {
                    if (query.startsWith("?")) {
                        answer[i] = lengthToCountMap.getOrDefault(query.length(), 0);
                        continue;
                    }
                    answer[i] = dfs(lengthToTrieMap.get(query.length()), query, 0);
                    continue;
                }
                StringBuilder reverseQuery = new StringBuilder();
                for (int j = query.length() - 1; j >= 0; j--) {
                    reverseQuery.append(query.charAt(j));
                }
                answer[i] = dfs(reverseLengthToTrieMap.get(query.length()), reverseQuery.toString(), 0);
            }

            // System.out.print(log);
            return answer;
        }

        // private void logTrie(Node[] nodes, int depth) {
        //     if (nodes == null) {
        //         return;
        //     }
        //     for (char c = 'a'; c <= 'z'; c++) {
        //         int letter = c - 'a';
        //         if (nodes[letter] == null) {
        //             continue;
        //         }
        //         for (int i = 0; i < depth; i++) {
        //             log.append("| ");
        //         }
        //         log.append(c).append(LN);
        //         logTrie(nodes[letter].children, depth + 1);
        //     }
        // }

        private int dfs(Map<Character, Node> nodes, String query, int depth) {
            if (depth == query.length()) {
                return nodes == null ? 1 : 0;
            }
            if (nodes == null) {
                return 0;
            }

            char c = query.charAt(depth);
            // for (int i = 0; i < depth; i++) {
            //     log.append("| ");
            // }
            // log.append(c).append(LN);

            if (c != '?') {
                Node n = nodes.get(c);
                if (n == null) {
                    return 0;
                }
                return dfs(n.children, query, depth + 1);
            }

            int result = 0;
            for (Node n : nodes.values()) {
                result += n.count;
            }
            return result;
        }

        private static class Node {

            public int count = 0;
            public Map<Character, Node> children;

        }

    }

}
