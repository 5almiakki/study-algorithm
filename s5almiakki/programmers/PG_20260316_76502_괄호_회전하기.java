import java.util.*;

public class PG_20260316_76502_괄호_회전하기 {

    class Solution {

        public int solution(String s) {
            Queue<Character> queue = new ArrayDeque<>();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                queue.add(s.charAt(i));
            }

            int answer = 0;
            for (int i = 0; i < length; i++) {
                // System.out.print(queue);
                if (isValid(queue)) {
                    // System.out.println(" valid");
                    answer++;
                    // } else {
                    //     System.out.println();
                }
                queue.add(queue.remove());
            }
            return answer;
        }

        boolean isValid(Queue<Character> queue) {
            Deque<Character> stack = new ArrayDeque<>();
            for (Character element : queue) {
                switch (element) {
                    case '(': // fall through
                    case '[': // fall through
                    case '{':
                        stack.push(element);
                        continue;
                    case ')':
                        if (stack.isEmpty() || stack.peek() != '(') {
                            return false;
                        }
                        break;
                    case ']':
                        if (stack.isEmpty() || stack.peek() != '[') {
                            return false;
                        }
                        break;
                    case '}':
                        if (stack.isEmpty() || stack.peek() != '{') {
                            return false;
                        }
                        break;
                }
                stack.pop();
            }
            return stack.isEmpty();
        }

    }

}
