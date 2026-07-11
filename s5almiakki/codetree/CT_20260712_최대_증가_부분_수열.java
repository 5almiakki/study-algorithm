import java.io.*;

public class CT_20260712_최대_증가_부분_수열 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int seqLength = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            int[] seq = new int[seqLength];
            for (int i = 0; i < seqLength; i++) {
                seq[i] = Integer.parseInt(input[i]);
            }
            int answer = 0;
            int[] dp = new int[seqLength];
            for (int i = 0; i < seqLength; i++) {
                for (int j = 0; j <= i; j++) {
                    if (seq[i] > seq[j] && dp[i] < dp[j]) {
                        dp[i] = dp[j];
                    }
                }
                dp[i]++;
                if (answer < dp[i]) {
                    answer = dp[i];
                }
            }
            System.out.print(answer);
        }

    }

}
