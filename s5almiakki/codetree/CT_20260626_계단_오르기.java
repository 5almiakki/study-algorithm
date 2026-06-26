import java.io.*;

public class CT_20260626_계단_오르기  {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            if (n <= 2) {
                System.out.print(1);
                return;
            }
            int[] dp = new int[n];
            dp[1] = 1;
            dp[2] = 1;
            for (int i = 3; i < n; i++) {
                dp[i] = (dp[i - 2] + dp[i - 3]) % 10007;
            }
            System.out.print(dp[n - 1]);
        }

    }

}
