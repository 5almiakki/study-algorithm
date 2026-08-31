import java.io.*;
//import java.util.*;

public class CT_20260831_도전_369게임 {

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
			int inputDigit = input.charAt(0) - '0';
			boolean multiple3Present = isMultiple3(inputDigit);
			int digitSum = inputDigit;
			long[][] dp = new long[digitCount][3];
			// 맨 왼쪽(제일 큰) 자리 초기화
			for (int digit = 0; digit < inputDigit; digit++) {
				if (isMultiple3(digit)) {
					answer = (answer + power10[digitCount - 1]) % DIVISOR;
					continue;
				}
				dp[0][digit % 3]++;
			}
			// System.out.println(Arrays.toString(dp[0]));

			for (int i = 1; i < digitCount; i++) {
				// 이전 digit < 이전 inputDigit인 경우
				// 현재 digit이 0 ~ 9인 경우를 모두 계산
				for (int digit = 0; digit <= 9; digit++) {
					if (isMultiple3(digit)) {
						long temp = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % DIVISOR;
						temp = (temp * power10[digitCount - 1 - i]) % DIVISOR;
						answer = (answer + temp) % DIVISOR;
						continue;
					}
					for (int prevRemainder = 0; prevRemainder <= 2; prevRemainder++) {
						int remainder = (digit + prevRemainder) % 3;
						dp[i][remainder] = (dp[i][remainder] + dp[i - 1][prevRemainder]) % DIVISOR;
					}
				}

				// 이전 digit == 이전 inputDigit인 경우
				// 현재 digit < 현재 inputDigit인 경우만 계산
				inputDigit = input.charAt(i) - '0';
				for (int digit = 0; digit < inputDigit; digit++) {
					if (multiple3Present || isMultiple3(digit)) {
						answer = (answer + power10[digitCount - 1 - i]) % DIVISOR;
						continue;
					}
					dp[i][(digit + digitSum) % 3] = (dp[i][(digit + digitSum) % 3] + 1L) % DIVISOR;
				}
				// System.out.println(Arrays.toString(dp[i]));
				if (isMultiple3(inputDigit)) {
					multiple3Present = true;
					continue;
				}
				digitSum += inputDigit;
			}

			// input 값 자체 처리
			if (multiple3Present) {
				answer = (answer + 1L) % DIVISOR;
			} else {
				dp[digitCount - 1][digitSum % 3] = (dp[digitCount - 1][digitSum % 3] + 1L) % DIVISOR;
			}
			// dp[digitCount - 1][0]에 있는 모든 자리가 0인 경우 제외
			answer = (answer + dp[digitCount - 1][0] + DIVISOR - 1L) % DIVISOR;
			System.out.print(answer);
		}

		static boolean isMultiple3(int n) {
			return n > 0 && n % 3 == 0;
		}

	}

}
