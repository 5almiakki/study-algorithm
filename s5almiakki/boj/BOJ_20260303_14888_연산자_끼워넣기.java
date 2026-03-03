import java.io.*;
import java.util.function.*;

public class BOJ_20260303_14888_연산자_끼워넣기 {

    public static class Main1 {

        static final IntBinaryOperator[] operators = {
                (left, right) -> left + right,
                (left, right) -> left - right,
                (left, right) -> left * right,
                (left, right) -> left / right
        };

        static int max = Integer.MIN_VALUE;
        static int min = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            try (InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr)) {
                int operandCount = Integer.parseInt(br.readLine());
                String[] input = br.readLine().split(" ");
                int[] operands = new int[operandCount];
                for (int i = 0; i < operandCount; i++) {
                    operands[i] = Integer.parseInt(input[i]);
                }
                input = br.readLine().split(" ");
                int[] operatorCounts = new int[4];
                for (int i = 0; i < operatorCounts.length; i++) {
                    operatorCounts[i] = Integer.parseInt(input[i]);
                }
                dfs(operands, operatorCounts, operands[0], 1);
                System.out.print(max + System.lineSeparator() + min);
            }
        }

        static void dfs(int[] operands, int[] operatorCounts, int previousResult, int depth) {
            if (depth == operands.length) {
                max = Math.max(max, previousResult);
                min = Math.min(min, previousResult);
                return;
            }

            for (int i = 0; i < operators.length; i++) {
                if (operatorCounts[i] == 0) {
                    continue;
                }
                int result = operators[i].applyAsInt(previousResult, operands[depth]);
                operatorCounts[i]--;
                dfs(operands, operatorCounts, result, depth + 1);
                operatorCounts[i]++;
            }
        }

    }

    public static class Main2 {

        static int max = Integer.MIN_VALUE;
        static int min = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            try (InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr)) {
                int operandCount = Integer.parseInt(br.readLine());
                String[] input = br.readLine().split(" ");
                int[] operands = new int[operandCount];
                for (int i = 0; i < operandCount; i++) {
                    operands[i] = Integer.parseInt(input[i]);
                }
                input = br.readLine().split(" ");
                int[] operatorCounts = new int[4];
                for (int i = 0; i < operatorCounts.length; i++) {
                    operatorCounts[i] = Integer.parseInt(input[i]);
                }
                dfs(operands, operatorCounts, operands[0], 1);
                System.out.print(max + System.lineSeparator() + min);
            }
        }

        static void dfs(int[] operands, int[] operatorCounts, int previousResult, int depth) {
            if (depth == operands.length) {
                max = Math.max(max, previousResult);
                min = Math.min(min, previousResult);
                return;
            }

            int newDepth = depth + 1;
            if (operatorCounts[0] > 0) {
                int result = previousResult + operands[depth];
                operatorCounts[0]--;
                dfs(operands, operatorCounts, result, newDepth);
                operatorCounts[0]++;
            }
            if (operatorCounts[1] > 0) {
                int result = previousResult - operands[depth];
                operatorCounts[1]--;
                dfs(operands, operatorCounts, result, newDepth);
                operatorCounts[1]++;
            }
            if (operatorCounts[2] > 0) {
                int result = previousResult * operands[depth];
                operatorCounts[2]--;
                dfs(operands, operatorCounts, result, newDepth);
                operatorCounts[2]++;
            }
            if (operatorCounts[3] > 0) {
                int result = previousResult / operands[depth];
                operatorCounts[3]--;
                dfs(operands, operatorCounts, result, newDepth);
                operatorCounts[3]++;
            }
        }

    }

}
