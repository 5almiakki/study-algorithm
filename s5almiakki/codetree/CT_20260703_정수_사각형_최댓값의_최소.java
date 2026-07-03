import java.io.*;

public class CT_20260703_정수_사각형_최댓값의_최소 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[n];
            String[] input = br.readLine().split(" ");
            dp[0] = Integer.parseInt(input[0]);
            for (int col = 1; col < n; col++) {
                dp[col] = Math.max(dp[col - 1], Integer.parseInt(input[col]));
            }
            for (int row = 1; row < n; row++) {
                input = br.readLine().split(" ");
                int cell = Integer.parseInt(input[0]);
                // System.out.printf("%7d", cell);
                if (dp[0] < cell) {
                    dp[0] = cell;
                }
                for (int col = 1; col < n; col++) {
                    cell = Integer.parseInt(input[col]);
                    // System.out.printf("%7d", cell);
                    dp[col] = Math.max(
                            Math.min(dp[col - 1], dp[col]),
                            cell
                    );
                }
                // System.out.println();
            }
            System.out.print(dp[n - 1]);
        }

    }

}
