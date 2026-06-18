import java.io.*;

public class CT_20260618_N개_중에_M개_뽑기 {

    public class Main {

        static StringBuilder answer = new StringBuilder();

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            int maxNum = Integer.parseInt(input[0]);
            int choiceCount = Integer.parseInt(input[1]);
            int[] chosenNums = new int[choiceCount];
            backtrack(maxNum, chosenNums, 1, 0);
            System.out.print(answer);
        }

        static void backtrack(int maxNum, int[] chosenNums, int beginNum, int depth) {
            if (depth == chosenNums.length) {
                for (int chosenNum : chosenNums) {
                    answer.append(chosenNum).append(' ');
                }
                answer.deleteCharAt(answer.length() - 1).append(System.lineSeparator());
                return;
            }
            for (int num = beginNum; num <= maxNum; num++) {
                chosenNums[depth] = num;
                backtrack(maxNum, chosenNums, num + 1, depth + 1);
            }
        }

    }

}
