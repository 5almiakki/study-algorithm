import java.io.*;

public class CT_20260528_K개_중에_1개를_N번_뽑기 {

    public class Main {

        static final String LN = System.lineSeparator();

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] line = br.readLine().split(" ");
            int k = Integer.parseInt(line[0]);
            int n = Integer.parseInt(line[1]);
            StringBuilder answer = new StringBuilder();
            dfs(k, n, new int[n], 0, answer);
            System.out.print(answer);
        }

        static void dfs(int k, int n, int[] choices, int depth, StringBuilder answer) {
            if (depth == n) {
                for (int choice : choices) {
                    answer.append(choice).append(' ');
                }
                answer.deleteCharAt(answer.length() - 1).append(LN);
                return;
            }

            for (int i = 1; i <= k; i++) {
                choices[depth] = i;
                dfs(k, n, choices, depth + 1, answer);
            }
        }

    }

}
