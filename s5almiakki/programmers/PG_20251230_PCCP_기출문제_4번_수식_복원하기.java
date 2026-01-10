import java.util.*;

public class PG_20251230_PCCP_기출문제_4번_수식_복원하기 {

    class Solution {

        public String[] solution(String[] expressions) {
            List<String> unknownExpressions = new ArrayList<>();
            List<String[]> splittedUnknownExpressions = new ArrayList<>();
            int maxNum = 1;
            for (String expression : expressions) {
                String[] splitted = expression.split(" ");
                if (splitted[4].charAt(0) == 'X') {
                    unknownExpressions.add(expression);
                    splittedUnknownExpressions.add(splitted);
                }
                for (int i = 0; i < splitted.length; i += 2) {
                    String operand = splitted[i];
                    if (operand.charAt(0) == 'X') {
                        continue;
                    }
                    for (int j = operand.length() - 1; j >= 0; j--) {
                        maxNum = Math.max(maxNum, operand.charAt(j) - '0');
                    }
                }
            }

            List<Integer> radixes = new ArrayList<>();
            for (int radix = maxNum + 1; radix < 10; radix++) {
                boolean valid = true;
                for (String expression : expressions) {
                    String[] splitted = expression.split(" ");
                    if (splitted[4].charAt(0) == 'X') {
                        continue;
                    }
                    int operand1 = Integer.parseInt(splitted[0], radix);
                    int operand2 = Integer.parseInt(splitted[2], radix);
                    int result = Integer.parseInt(splitted[4], radix);
                    switch (splitted[1]) {
                        case "+":
                            if (operand1 + operand2 != result) {
                                valid = false;
                            }
                            break;
                        case "-":
                            if (operand1 - operand2 != result) {
                                valid = false;
                            }
                            break;
                    }
                }
                if (valid) {
                    radixes.add(radix);
                }
            }

            String[] answer = new String[unknownExpressions.size()];
            for (int i = answer.length - 1; i >= 0; i--) {
                String[] splittedUnknownExpression = splittedUnknownExpressions.get(i);
                boolean canDetermine = true;
                String prevResult = null;
                for (int radix : radixes) {
                    int operand1 = Integer.parseInt(splittedUnknownExpression[0], radix);
                    int operand2 = Integer.parseInt(splittedUnknownExpression[2], radix);
                    String result = splittedUnknownExpression[1].equals("+")
                            ? Integer.toString(operand1 + operand2, radix)
                                    : Integer.toString(operand1 - operand2, radix);
                    if (prevResult == null) {
                        prevResult = result;
                    } else if (!result.equals(prevResult)) {
                        canDetermine = false;
                        break;
                    }
                }
                answer[i] = unknownExpressions.get(i).replace(
                        "X",
                        canDetermine ? prevResult : "?");
            }
            return answer;
        }

    }

}
