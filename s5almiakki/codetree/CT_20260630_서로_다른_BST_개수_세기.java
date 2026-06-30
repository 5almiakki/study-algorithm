import java.io.*;

public class CT_20260630_서로_다른_BST_개수_세기 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            if (n == 1) {
                System.out.print(1);
                return;
            }
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int nodeCount = 2; nodeCount <= n; nodeCount++) {
                for (int oldNodeCount = nodeCount - 1; oldNodeCount >= 0; oldNodeCount--) {
                    dp[nodeCount] += dp[oldNodeCount] * dp[nodeCount - 1 - oldNodeCount];
                }
            }
            System.out.print(dp[n]);
        }

    }

}
