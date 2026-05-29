import java.io.*;
import java.util.*;

public class CT_20260529_겹치지_않게_선분_고르기 {

    public class Main {

        static int answer = 0;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int lineCount = Integer.parseInt(br.readLine());
            int[][] lines = new int[lineCount][2];
            for (int i = 0; i < lineCount; i++) {
                String[] input = br.readLine().split(" ");
                lines[i][0] = Integer.parseInt(input[0]);
                lines[i][1] = Integer.parseInt(input[1]);
            }
            dfs(lines, new ArrayDeque<>(), 0);
            System.out.print(answer);
        }

        static void dfs(int[][] lines, Deque<int[]> chosenLines, int depth) {
            if (lines.length == depth) {
                int[] accumulations = new int[1002];
                for (int[] chosenLine : chosenLines) {
                    accumulations[chosenLine[0]]++;
                    accumulations[chosenLine[1] + 1]--;
                }
                for (int i = 1; i < 1002; i++) {
                    accumulations[i] += accumulations[i - 1];
                    if (accumulations[i] >= 2) {
                        return;
                    }
                }
                // StringBuilder log = new StringBuilder();
                // for (int i = 1; i <= 10; i++) {
                //     log.append(accumulations[i]);
                //     if (i % 100 == 0) {
                //         log.append(System.lineSeparator());
                //     }
                // }
                // System.out.println(log);
                answer = Math.max(answer, chosenLines.size());
                return;
            }
            // 현재 선분 안 고르고 계산
            dfs(lines, chosenLines, depth + 1);
            // 골랐던 선분 중 현재 선분과 겹치는 게 있는지 검사
            // for (int[] chosenLine : chosenLines) {
            //     if (chosenLine[0] <= lines[depth][0] && lines[depth][0] <= chosenLine[1]
            //             || chosenLine[0] <= lines[depth][1] && lines[depth][1] <= chosenLine[1]) {
            //         return;
            //     }
            // }
            // 겹치는 게 없으면 현재 선분 고르고 계산
            chosenLines.push(lines[depth]);
            dfs(lines, chosenLines, depth + 1);
            chosenLines.pop();
        }

    }

}
