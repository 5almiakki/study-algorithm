import java.io.*;
import java.util.*;

public class CT_20260714_최대_점프_횟수 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int seqLength = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            int[] distances = new int[seqLength];
            for (int i = 0; i < seqLength; i++) {
                distances[i] = Integer.parseInt(input[i]);
            }
            int[] dp = new int[seqLength];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            int answer = 0;
            for (int i = 0; i < seqLength; i++) {
                if (dp[i] == -1) {
                    continue;
                }
                answer = Math.max(answer, dp[i]);
                int newJumpCount = dp[i] + 1;
                int bound = Math.min(distances[i] + 1, seqLength - i);
                for (int distance = 1; distance < bound; distance++) {
                    dp[i + distance] = Math.max(dp[i + distance], newJumpCount);
                }
            }
            // System.out.println(Arrays.toString(dp));
            System.out.print(answer);
        }

    }

}
