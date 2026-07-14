import java.io.*;

public class CT_20260714_최대_감소_부분_수열 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int seqLength = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            int[] seq = new int[seqLength];
            for (int i = 0; i < seqLength; i++) {
                seq[i] = Integer.parseInt(input[i]);
            }
            int[] dp = new int[seqLength];
            int answer = 1;
            dp[0] = 1;
            for (int tail = 1; tail < seqLength; tail++) {
                for (int head = 0; head < tail; head++) {
                    if (seq[head] > seq[tail]) {
                        dp[tail] = Math.max(dp[tail], dp[head]);
                    }
                }
                dp[tail]++;
                answer = Math.max(answer, dp[tail]);
            }
            System.out.print(answer);
        }

    }

}
