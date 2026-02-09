
public class PG_20260209_43163_단어_변환 {

    class Solution {

        int answer = Integer.MAX_VALUE;

        public int solution(String begin, String target, String[] words) {
            dfs(words, new boolean[words.length], target, begin, 0);
            return answer == Integer.MAX_VALUE ? 0 : answer;
        }

        void dfs(String[] words, boolean[] visited, String target, String chosenWord, int depth) {
            if (target.equals(chosenWord)) {
                answer = Math.min(answer, depth);
                return;
            }

            for (int i = 0; i < words.length; i++) {
                if (visited[i] || !canConvert(chosenWord, words[i])) {
                    continue;
                }
                visited[i] = true;
                dfs(words, visited, target, words[i], depth + 1);
                visited[i] = false;
            }
        }

        boolean canConvert(String word1, String word2) {
            int length = word1.length();
            int count = 0;
            for (int i = 0; i < length; i++) {
                char c1 = word1.charAt(i);
                char c2 = word2.charAt(i);
                if (c1 != c2) {
                    count++;
                    if (count > 1) {
                        return false;
                    }
                }
            }
            return true;
        }

    }

}
