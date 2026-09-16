import java.io.*;

public class CT_20260916_도전_369게임 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			int digitCount = input.length();
			long divisor = 1_000_000_007L;
			long[] power10 = new long[digitCount];
			power10[0] = 1L;
			for (int i = 1; i < digitCount; i++) {
				power10[i] = (power10[i - 1] * 10L) % divisor;
			}

			long answer = 0L;
			long[][] dp = new long[digitCount][3]; // dp[digitIdx][digitSumMod3] = caseCount
			int inputDigit = input.charAt(0) - '0';
			for (int digit = 0; digit < inputDigit; digit++) {
				if (isMultiple3(digit)) {
					answer = (answer + power10[digitCount - 1]) % divisor;
				} else {
					dp[0][digit % 3]++;
				}
			}
			int digitSum = inputDigit;
			boolean multiple3Present = isMultiple3(inputDigit);
			for (int i = 1; i < digitCount; i++) {
				for (int digit = 0; digit <= 9; digit++) {
					if (isMultiple3(digit)) {
						long temp = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % divisor;
						temp = (temp * power10[digitCount - 1 - i]) % divisor;
						answer = (answer + temp) % divisor;
						continue;
					}
					for (int pastRemainder = 0; pastRemainder <= 2; pastRemainder++) {
						int remainder = (pastRemainder + digit) % 3;
						dp[i][remainder] += dp[i - 1][pastRemainder];
						dp[i][remainder] %= divisor;
					}
				}
				inputDigit = input.charAt(i) - '0';
				for (int digit = 0; digit < inputDigit; digit++) {
					if (multiple3Present || isMultiple3(digit)) {
						answer = (answer + power10[digitCount - 1 - i]) % divisor;
						continue;
					}
					dp[i][(digitSum + digit) % 3]++;
					dp[i][(digitSum + digit) % 3] %= divisor;
				}
				if (isMultiple3(inputDigit)) {
					multiple3Present = true;
				} else {
					digitSum += inputDigit;
				}
			}
			if (multiple3Present) {
				answer = (answer + 1L) % divisor;
			} else {
				dp[digitCount - 1][digitSum % 3]++;
				dp[digitCount - 1][digitSum % 3] %= divisor;
			}
			answer = (answer + dp[digitCount - 1][0] + divisor - 1) % divisor;
			System.out.print(answer);
		}

		static boolean isMultiple3(int n) {
			return n > 0 && n % 3 == 0;
		}

	}

}
