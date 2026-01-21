
public class PG_20260121_17685_3차_자동완성 {

    class Solution {

        public int solution(String[] words) {
            Node[] root = new Node['z' - 'a' + 1];
            for (String word : words) {
                int length = word.length();
                Node[] nodes = root;
                for (int i = 0; i < length; i++) {
                    int letter = word.charAt(i) - 'a';
                    Node n = nodes[letter];
                    if (n == null) {
                        n = new Node();
                        nodes[letter] = n;
                    }
                    n.count++;
                    nodes = n.nodes;
                }
            }

            int answer = 0;
            for (String word : words) {
                int length = word.length();
                int inputCount = length;
                Node[] nodes = root;
                for (int i = 0; i < length; i++) {
                    int letter = word.charAt(i) - 'a';
                    Node n = nodes[letter];
                    if (n.count == 1) {
                        inputCount = i + 1;
                        break;
                    }
                    nodes = n.nodes;
                }
                answer += inputCount;
            }

            return answer;
        }

        private static class Node {

            public int count;
            public Node[] nodes;

            public Node() {
                this.count = 0;
                this.nodes = new Node['z' - 'a' + 1];
            }

        }

    }

}
