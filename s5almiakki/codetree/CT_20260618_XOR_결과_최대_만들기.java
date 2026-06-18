import java.io.*;

public class CT_20260618_XOR_결과_최대_만들기 {

    public class Main {

        static int answer = Integer.MIN_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            int numCount = Integer.parseInt(input[0]);
            int choiceCount = Integer.parseInt(input[1]);
            int[] nums = new int[numCount];
            input = br.readLine().split(" ");
            for (int i = 0; i < numCount; i++) {
                nums[i] = Integer.parseInt(input[i]);
            }
            int[] chosenNums = new int[choiceCount];
            backtrack(nums, chosenNums, 0, 0);
            System.out.print(answer);
        }

        static void backtrack(int[] nums, int[] chosenNums, int beginIdx, int depth) {
            if (chosenNums.length == depth) {
                int result = chosenNums[0];
                for (int i = 1; i < chosenNums.length; i++) {
                    result ^= chosenNums[i];
                }
                if (answer < result) {
                    answer = result;
                }
                return;
            }
            for (int idx = beginIdx; idx < nums.length; idx++) {
                chosenNums[depth] = nums[idx];
                backtrack(nums, chosenNums, idx + 1, depth + 1);
            }
        }

    }

}
