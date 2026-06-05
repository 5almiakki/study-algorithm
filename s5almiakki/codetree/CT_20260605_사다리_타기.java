import java.io.*;
import java.util.*;

public class CT_20260605_사다리_타기 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            int verticalLineCount = Integer.parseInt(input[0]);
            int horizontalLineCount = Integer.parseInt(input[1]);
            int[] targetMap = new int[verticalLineCount];
            for (int i = 0; i < verticalLineCount; i++) {
                targetMap[i] = i;
            }
            int[] map = Arrays.copyOf(targetMap, targetMap.length);
            int[][] horizontalLines = new int[horizontalLineCount][2];
            for (int i = 0; i < horizontalLineCount; i++) {
                input = br.readLine().split(" ");
                horizontalLines[i][0] = Integer.parseInt(input[0]) - 1;
                horizontalLines[i][1] = Integer.parseInt(input[1]) - 1;
            }
            Arrays.sort(horizontalLines, (o1, o2) -> Integer.compare(o1[1], o2[1]));
            for (int[] horizontalLine : horizontalLines) {
                int idx1 = horizontalLine[0];
                int idx2 = horizontalLine[0] + 1;
                int tmp = targetMap[idx1];
                targetMap[idx1] = targetMap[idx2];
                targetMap[idx2] = tmp;
            }
            int answer = horizontalLineCount;
            for (int targetSwapCount = 0; targetSwapCount < horizontalLineCount; targetSwapCount++) {
                if(dfs(targetMap, map, targetSwapCount, 0)) {
                    answer = targetSwapCount;
                    break;
                }
            }
            System.out.print(answer);
        }

        static boolean dfs(int[] targetMap, int[] map, int targetSwapCount, int swapCount) {
            if (targetSwapCount == swapCount) {
                for (int i = 0; i < map.length; i++) {
                    if (targetMap[i] != map[i]) {
                        return false;
                    }
                }
                return true;
            }
            for (int i = 1; i < map.length; i++) {
                int tmp = map[i];
                map[i] = map[i - 1];
                map[i - 1] = tmp;
                boolean result = dfs(targetMap, map, targetSwapCount, swapCount + 1);
                tmp = map[i];
                map[i] = map[i - 1];
                map[i - 1] = tmp;
                if (result) {
                    return true;
                }
            }
            return false;
        }

    }

}
