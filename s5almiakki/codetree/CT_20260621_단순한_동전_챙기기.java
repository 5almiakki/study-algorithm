import java.io.*;

public class CT_20260621_단순한_동전_챙기기 {

    public class Main {

        static int answer = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int gridSize = Integer.parseInt(br.readLine());
            int[] endPoint = null;
            int[][] points = new int[10][];
            for (int row = 0; row < gridSize; row++) {
                String line = br.readLine();
                for (int col = 0; col < gridSize; col++) {
                    char letter = line.charAt(col);
                    switch (letter) {
                        case '.':
                            continue;
                        case 'S':
                            points[0] = new int[] { row, col };
                            break;
                        case 'E':
                            endPoint = new int[] { row, col };
                            break;
                        default:
                            points[letter - '0'] = new int[] { row, col };
                            break;
                    }
                }
            }
            backtrack(endPoint, points, 0, 0, 0);
            System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
        }

        static void backtrack(int[] endPoint, int[][] points, int choiceCount, int prevIdx, int sum) {
            // for (int i = 0; i < choiceCount; i++) {
            // System.out.print(" ");
            // }
            // System.out.println("choiceCount: " + choiceCount + ", prevIdx: " + prevIdx);

            if (choiceCount == 3) {
                sum += Math.abs(endPoint[0] - points[prevIdx][0]) + Math.abs(endPoint[1] - points[prevIdx][1]);
                if (sum < answer) {
                    answer = sum;
                }
                return;
            }
            for (int i = prevIdx + 1; i < points.length; i++) {
                if (points[i] == null) {
                    continue;
                }
                int newSum = sum
                        + Math.abs(points[prevIdx][0] - points[i][0])
                        + Math.abs(points[prevIdx][1] - points[i][1]);
                if (answer <= newSum) {
                    continue;
                }
                backtrack(endPoint, points, choiceCount + 1, i, newSum);
            }
        }

    }

}
