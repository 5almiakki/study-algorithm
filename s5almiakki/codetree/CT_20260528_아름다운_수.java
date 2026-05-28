import java.io.*;

public class CT_20260528_아름다운_수 {

    public class Main {

        static final String[] NUMS = {
                "1", "22", "333", "4444"
        };

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            System.out.print(dfs(n, 0));
        }

        static int dfs(int n, int length) {
            int result = 0;
            for (int i = 1; i <= 4; i++) {
                int newLength = length + i;
                if (newLength == n) {
                    result++;
                    continue;
                }
                if (newLength < n) {
                    result += dfs(n, newLength);
                }
            }
            return result;
        }

    }

}
