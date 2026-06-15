import java.io.*;

public class CT_20260615_최소_점프_횟수 {

    public class Main {

        static int answer = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int pointCount = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            int[] points = new int[pointCount];
            for (int i = 0; i < pointCount; i++) {
                points[i] = Integer.parseInt(input[i]);
            }
            backtrack(points, 0, 0);
            System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
        }

        static void backtrack(int[] points, int position, int jumpCount) {
            // StringBuilder sb = new StringBuilder();
            // for (int i = 0; i < jumpCount; i++) {
            // sb.append(" ");
            // }
            // System.out.println(sb.toString() + position);

            if (position >= points.length) {
                return;
            }
            if (position == points.length - 1) {
                if (jumpCount < answer) {
                    answer = jumpCount;
                }
                return;
            }
            for (int jumpAmount = 1; jumpAmount <= points[position]; jumpAmount++) {
                backtrack(points, position + jumpAmount, jumpCount + 1);
            }
        }

    }

}
