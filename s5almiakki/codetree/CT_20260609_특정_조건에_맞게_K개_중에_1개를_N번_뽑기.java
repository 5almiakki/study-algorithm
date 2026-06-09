import java.io.*;

public class CT_20260609_특정_조건에_맞게_K개_중에_1개를_N번_뽑기 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);
            int[] nums = new int[n];
            StringBuilder answer = new StringBuilder();
            backtrack(answer, k, nums, 0);
            System.out.print(answer);
        }

        static void backtrack(StringBuilder answer, int k, int[] nums, int depth) {
            if (nums.length == depth) {
                for (int num : nums) {
                    answer.append(num).append(' ');
                }
                answer.deleteCharAt(answer.length() - 1).append(System.lineSeparator());
                return;
            }
            for (int i = 1; i <= k; i++) {
                try {
                    if (i == nums[depth - 1] && i == nums[depth - 2]) {
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                nums[depth] = i;
                backtrack(answer, k, nums, depth + 1);
            }
        }

    }

}
