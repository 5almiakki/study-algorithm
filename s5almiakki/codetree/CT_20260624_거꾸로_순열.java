import java.io.*;

public class CT_20260624_거꾸로_순열 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            int[] chosenNums = new int[n];
            StringBuilder answer = new StringBuilder();
            backtrack(answer, n, chosenNums, 0, 0);
            System.out.print(answer);
        }

        static void backtrack(StringBuilder answer, int n, int[] chosenNums, int depth, int visitedMask) {
            if (depth == n) {
                for (int chosenNum : chosenNums) {
                    answer.append(chosenNum).append(' ');
                }
                answer.deleteCharAt(answer.length() - 1).append(System.lineSeparator());
                return;
            }
            for (int i = n; i >= 1; i--) {
                int mask = 1 << i;
                if ((visitedMask & mask) != 0) {
                    continue;
                }
                chosenNums[depth] = i;
                backtrack(answer, n, chosenNums, depth + 1, visitedMask | mask);
            }
        }

    }

}
