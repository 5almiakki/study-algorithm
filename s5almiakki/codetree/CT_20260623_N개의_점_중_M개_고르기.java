import java.io.*;

public class CT_20260623_N개의_점_중_M개_고르기 {

    public class Main {

        static int answer = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            int pointCount = Integer.parseInt(input[0]);
            int choiceCount = Integer.parseInt(input[1]);
            int[][] points = new int[pointCount][];
            for (int i = 0; i < pointCount; i++) {
                input = br.readLine().split(" ");
                points[i] = new int[] {
                        Integer.parseInt(input[0]),
                        Integer.parseInt(input[1])
                };
            }
            int[] chosenIndices = new int[choiceCount];
            backtrack(points, chosenIndices, 0, 0);
            System.out.print(answer);
        }

        static void backtrack(int[][] points, int[] chosenIndices, int depth, int beginIdx) {
            if (depth == chosenIndices.length) {
                // for (int i : chosenIndices) {
                // System.out.print(i + " ");
                // }
                // System.out.println();
                int maxDistance = 0;
                for (int i = chosenIndices.length - 1; i >= 1; i--) {
                    int[] point1 = points[chosenIndices[i]];
                    for (int j = i - 1; j >= 0; j--) {
                        int[] point2 = points[chosenIndices[j]];
                        int dx = point1[0] - point2[0];
                        int dy = point1[1] - point2[1];
                        int distance = dx * dx + dy * dy;
                        if (maxDistance < distance) {
                            maxDistance = distance;
                        }
                    }
                }
                if (maxDistance < answer) {
                    answer = maxDistance;
                }
                // System.out.println("maxDistance: " + maxDistance);
                return;
            }
            int endIdx = points.length - chosenIndices.length + depth;
            for (int i = beginIdx; i <= endIdx; i++) {
                chosenIndices[depth] = i;
                backtrack(points, chosenIndices, depth + 1, i + 1);
            }
        }

    }

}
