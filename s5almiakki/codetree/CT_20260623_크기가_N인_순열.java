import java.io.*;

public class CT_20260623_크기가_N인_순열 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            int[] chosenNums = new int[n];
            StringBuilder answer = new StringBuilder();
            backtrack(n, chosenNums, 0, answer, 0);
            System.out.print(answer);
        }

        static void backtrack(int n, int[] chosenNums, int chosenMask, StringBuilder answer, int depth) {
            if (depth == n) {
                for (int chosenNum : chosenNums) {
                    answer.append(chosenNum).append(' ');
                }
                answer.deleteCharAt(answer.length() - 1).append(System.lineSeparator());
                return;
            }
            for (int i = 1; i <= n; i++) {
                int mask = 1 << i;
                if ((chosenMask & mask) != 0) {
                    continue;
                }
                chosenNums[depth] = i;
                backtrack(n, chosenNums, chosenMask | mask, answer, depth + 1);
            }
        }

    }

}
