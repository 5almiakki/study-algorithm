import java.io.*;

public class CT_20260613_가능한_수열_중_최솟값_구하기 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int targetLength = Integer.parseInt(br.readLine());
            StringBuilder answer = new StringBuilder();
            backtrack(targetLength, answer);
            System.out.print(answer);
        }

        static boolean backtrack(int targetLength, StringBuilder sb) {
            if (!isValid(sb)) {
                return false;
            }
            if (targetLength == sb.length()) {
                return true;
            }
            boolean found = false;
            for (char c = '4'; c <= '6'; c++) {
                sb.append(c);
                found = backtrack(targetLength, sb);
                if (found) {
                    return true;
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            return false;
        }

        static boolean isValid(StringBuilder sb) {
            // System.out.println("sb: " + sb);
            int length = sb.length();
            int halfLength = length >> 1;
            for (int i = 1; i <= halfLength; i++) {
                int border = length - i;
                String s1 = sb.substring(border);
                String s2 = sb.substring(border - i, border);
                // System.out.println("substrings: " + s2 + ' ' + s1);
                if (s1.equals(s2)) {
                    return false;
                }
            }
            return true;
        }

    }

}
