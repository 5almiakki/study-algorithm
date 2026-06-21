import java.io.*;

public class CT_20260621_2N개_중에_N개의_숫자를_적절하게_고르기 {

    public class Main {

        static int answer = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            int[] sequence = new int[n << 1];
            String[] input = br.readLine().split(" ");
            for (int i = 0; i < sequence.length; i++) {
                sequence[i] = Integer.parseInt(input[i]);
            }
            backtrack(sequence, 1, 1, 1);
            System.out.print(answer);
        }

        static void backtrack(int[] sequence, int chosenMask, int choiceCount, int beginIdx) {
            if (choiceCount == (sequence.length >> 1)) {
                int sum1 = 0;
                int sum2 = 0;
                for (int i = 0; i < sequence.length; i++) {
                    int mask = 1 << i;
                    if ((mask & chosenMask) == 0) {
                        sum1 += sequence[i];
                    } else {
                        sum2 += sequence[i];
                    }
                }
                int diff = Math.abs(sum1 - sum2);
                if (diff < answer) {
                    answer = diff;
                }
                return;
            }
            for (int i = beginIdx; i < sequence.length; i++) {
                backtrack(sequence, chosenMask | (1 << i), choiceCount + 1, i + 1);
            }
        }

    }

}
