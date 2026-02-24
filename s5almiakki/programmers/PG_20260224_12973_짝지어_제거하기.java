import java.util.*;

public class PG_20260224_12973_짝지어_제거하기 {

    class Solution1 {

        public int solution(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
                    stack.push(s.charAt(i));
                    continue;
                }
                stack.pop();
            }
            return stack.isEmpty() ? 1 : 0;
        }

    }

    class Solution2 {

        public int solution(String s) {
            StringBuilder sb = new StringBuilder();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                int sbLength = sb.length();
                if (sbLength == 0) {
                    sb.append(c);
                    continue;
                }
                if (sb.charAt(sbLength - 1) == c) {
                    sb.deleteCharAt(sbLength - 1);
                    continue;
                }
                sb.append(c);
            }
            return sb.length() == 0 ? 1 : 0;
        }

    }

}
