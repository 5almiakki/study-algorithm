import java.io.*;

public class CT_20260824_도전_369게임 {

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
			// dp[i][j]:
			// N의 앞 i자리보다 이미 작고 길이가 i인 숫자열(앞자리 0 허용) 중,
			// 3, 6, 9를 포함하지 않고
			// 자릿수 합을 3으로 나눈 나머지가 j인 경우의 수
			// 3, 6, 9를 포함하는 경우는 바로 answer에 누적
			long[][] dp = new long[digitCount][3];
			int inputDigit = input.charAt(0) - '0';
			boolean multiple3Present = isMultiple3(inputDigit);
			int digitSum = inputDigit;
			// 맨 왼쪽 자릿수에 대한 dp[0] 초기화
			for (int num = 0; num < inputDigit; num++) {
				if (isMultiple3(num)) {
					answer = (answer + power10[digitCount - 1]) % DIVISOR;
					continue;
				}
				dp[0][num % 3]++;
			}

			for (int digitIdx = 1; digitIdx < digitCount; digitIdx++) {
				// 이전 digit이 이전 inputDigit보다 작은 경우의 계산
				// 현재 digit이 0 ~ 9인 경우 전부 고려 대상임
				for (int num = 0; num <= 9; num++) {
					if (isMultiple3(num)) {
						long temp = (dp[digitIdx - 1][0] + dp[digitIdx - 1][1] + dp[digitIdx - 1][2]) % DIVISOR;
						temp = (temp * power10[digitCount - 1 - digitIdx]) % DIVISOR;
						answer = (answer + temp) % DIVISOR;
						continue;
					}
					for (int prevRemainder = 0; prevRemainder < 3; prevRemainder++) {
						dp[digitIdx][(num + prevRemainder) % 3] =
								(dp[digitIdx][(num + prevRemainder) % 3] + dp[digitIdx - 1][prevRemainder]) % DIVISOR;
					}
				}

				// 이전 digit이 이전 inputDigit과 같은 경우의 계산
				// 현재 digit이 현재 inputDigit 이하인 경우만 고려 대상임
				inputDigit = input.charAt(digitIdx) - '0';
				// 현재 digit이 inputDigit보다 작은 경우의 dp
				for (int num = 0; num < inputDigit; num++) {
					if (multiple3Present || isMultiple3(num)) {
						answer = (answer + power10[digitCount - 1 - digitIdx]) % DIVISOR;
						continue;
					}
					dp[digitIdx][(num + digitSum) % 3] =
							(dp[digitIdx][(num + digitSum) % 3] + 1L) % DIVISOR;
				}

				// 현재 digit이 inputDigit과 같은 경우
				if (isMultiple3(inputDigit)) {
					multiple3Present = true;
					continue;
				}
				digitSum += inputDigit;
			}

			// 마지막 자릿수 처리
			if (multiple3Present) {
				// inputDigit들 중 3의 배수가 있으면 무조건 박수 1회 추가
				answer = (answer + 1L) % DIVISOR;
			} else {
				// 아니면 dp에 저장
				dp[digitCount - 1][digitSum % 3] =
						(dp[digitCount - 1][digitSum % 3] + 1L) % DIVISOR;
			}
			answer = (answer + dp[digitCount - 1][0] + (DIVISOR - 1L)) % DIVISOR;
			System.out.print(answer);
		}

		static boolean isMultiple3(int n) {
			return n != 0 && n % 3 == 0;
		}

	}

}
