import java.io.*;

public class CT_20260817_도전_369게임 {

	public class Main {

		static final long DIVISOR = 1_000_000_007L;

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			int digitCount = input.length();
			long[] power10 = new long[digitCount + 1];
			power10[0] = 1L;
			for (int i = 1; i <= digitCount; i++) {
				power10[i] = (power10[i - 1] * 10L) % DIVISOR;
			}

			long answer = 0L;
			long[][] dp = new long[digitCount + 1][3];
			boolean multiple3Present = false;
			int digitSum = 0;
			for (int digitIdx = 0; digitIdx < digitCount; digitIdx++) {
				for (int num = 0; num <= 9; num++) {
					if (num == 3 || num == 6 || num == 9) {
						long temp = (dp[digitIdx][0] + dp[digitIdx][1] + dp[digitIdx][2]) % DIVISOR;
						temp = (temp * power10[digitCount - 1 - digitIdx]) % DIVISOR;
						answer = (answer + temp) % DIVISOR;
						continue;
					}
					for (int offset = 0; offset < 3; offset++) {
						dp[digitIdx + 1][(num + offset) % 3] =
								(dp[digitIdx + 1][(num + offset) % 3] + dp[digitIdx][offset]) % DIVISOR;
					}
				}
				int inputDigit = input.charAt(digitIdx) - '0';
				for (int num = 0; num < inputDigit; num++) {
					if (multiple3Present || num == 3 || num == 6 || num == 9) {
						answer = (answer + power10[digitCount - 1 - digitIdx]) % DIVISOR;
						continue;
					}
					dp[digitIdx + 1][(num + digitSum) % 3] =
							(dp[digitIdx + 1][(num + digitSum) % 3] + 1L) % DIVISOR;
				}
				if (inputDigit == 3 || inputDigit == 6 || inputDigit == 9) {
					multiple3Present = true;
					continue;
				}
				digitSum += inputDigit;
			}

			if (multiple3Present) {
				answer = (answer + 1L) % DIVISOR;
			} else {
				dp[digitCount][digitSum % 3] =
						(dp[digitCount][digitSum % 3] + 1L) % DIVISOR;
			}
			answer = (answer + dp[digitCount][0] + (DIVISOR - 1L)) % DIVISOR;
			System.out.print(answer);
		}

	}

}
