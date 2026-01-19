import java.util.*;

public class PG_20260119_12929_올바른_괄호의_갯수 {

    class Solution {

        private static final Object LP = new Object();

        private boolean[] leftParentheses;
        private int answer = 0;

        // private StringBuilder log = new StringBuilder();
        // private String ln = System.lineSeparator();

        public int solution(int n) {
            leftParentheses = new boolean[n << 1];
            dfs(n, 0, 0);
            // System.out.print(log);
            return answer;
        }

        private void dfs(int n, int depth, int base) {
            if (n == depth) {
                // for (boolean leftParenthesis : leftParentheses) {
                //     log.append(leftParenthesis ? '(' : ')');
                // }
                // log.append(ln);

                if (isParenthesesValid()) {
                    answer++;
                }
                return;
            }
            for (int i = base; i < leftParentheses.length; i++) {
                leftParentheses[i] = true;
                dfs(n, depth + 1, i + 1);
                leftParentheses[i] = false;
            }
        }

        private boolean isParenthesesValid() {
            Deque<Object> stack = new ArrayDeque<>();
            for (boolean leftParenthesis : leftParentheses) {
                if (leftParenthesis) {
                    stack.push(LP);
                    continue;
                }
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
            return stack.isEmpty();
        }

    }

}
