import java.io.*;
import java.util.*;
import java.util.stream.*;

public class CT_20260609_알파벳과_사칙연산 {

    public class Main {

        static int answer = Integer.MIN_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String expr = br.readLine();
            Map<Character, Integer> letterToValueMap = new HashMap<>();
            for (int i = expr.length() - 1; i >= 0; i--) {
                char c = expr.charAt(i);
                if ('a' <= c && c <= 'f') {
                    letterToValueMap.put(c, null);
                }
            }
            List<Character> letters = letterToValueMap.keySet().stream().collect(Collectors.toList());
            backtrack(expr, letterToValueMap, letters, 0);
            System.out.print(answer);
        }

        static void backtrack(String expr, Map<Character, Integer> letterToValueMap, List<Character> letters,
                int depth) {
            if (depth == letterToValueMap.size()) {
                compute(expr, letterToValueMap);
                return;
            }
            for (int value = 1; value <= 4; value++) {
                letterToValueMap.put(letters.get(depth), value);
                backtrack(expr, letterToValueMap, letters, depth + 1);
            }
        }

        static void compute(String expr, Map<Character, Integer> letterToValueMap) {
            int result = letterToValueMap.get(expr.charAt(0));
            int length = expr.length();
            for (int i = 1; i < length; i += 2) {
                int operand = letterToValueMap.get(expr.charAt(i + 1));
                switch (expr.charAt(i)) {
                    case '+':
                        result += operand;
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                    case '/':
                        result /= operand;
                        break;
                }
            }
            if (answer < result) {
                answer = result;
            }
        }

    }

}
