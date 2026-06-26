import java.io.*;

public class CT_20260626_사각형_채우기_3 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            if (n == 1) {
                System.out.print(2);
                return;
            }
            if (n == 2) {
                System.out.print(7);
                return;
            }
            long[] dp = new long[n + 1];
            dp[0] = 1L;
            dp[1] = 2L;
            dp[2] = 7L;
            for (int i = 3; i <= n; i++) {
                dp[i] = (
                        (dp[i - 1] * 3L) + (dp[i - 2] + 1_000_000_007L - dp[i - 3])
                ) % 1_000_000_007L;
            }
            System.out.print(dp[n]);
        }

    }

/*
n = 1
*  |
*  |

n = 2
**  --  --
--  **  --
(a1 * 2 + 3)

n = 3
*--  --*
--*  *--

n = 4
*--*  ----
----  *--*

n = 5
*----  ----*
----*  *----

n = 6
*----*  ------
------  *----*

a(n)     = a(n - 1) << 1 + a(n - 2)  * 3 + a(n - 3) << 1 + a(n - 4) << 1 + a(n - 5) << 1 + ...
a(n - 1) =                 a(n - 2) << 1 + a(n - 3)  * 3 + a(n - 4) << 1 + a(n - 5) << 1 + ...
a(n) - a(n - 1) = a(n - 1) << 1 + a(n - 2) - a(n - 3)
a(n) = a(n - 1) * 3 + a(n - 2) - a(n - 3);
*/

}
