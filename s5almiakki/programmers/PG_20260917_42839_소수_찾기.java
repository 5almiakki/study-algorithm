import java.util.*;

public class PG_20260917_42839_소수_찾기 {

	class Solution {

		boolean[] isPrime;
		Set<Integer> primes = new HashSet<>();

		public int solution(String numbers) {
			int bound = 1;
			for (int i = numbers.length() - 1; i >= 0; i--) {
				bound *= 10;
			}
			isPrime = new boolean[bound];
			Arrays.fill(isPrime, true);
			isPrime[0] = false;
			isPrime[1] = false;
			for (int i = 2; i < bound; i++) {
				if (!isPrime[i]) {
					continue;
				}
				for (int j = i << 1; j < bound; j += i) {
					isPrime[j] = false;
				}
			}
			dfs(numbers, 0, 0);
			return primes.size();
		}

		void dfs(String numbers, int chosenMask, int num) {
			if (isPrime[num]) {
				primes.add(num);
			}
			if (Integer.bitCount(chosenMask) == numbers.length()) {
				return;
			}
			for (int i = numbers.length() - 1; i >= 0; i--) {
				int mask = 1 << i;
				if ((mask & chosenMask) != 0) {
					continue;
				}
				dfs(numbers, chosenMask | mask, num * 10 + (numbers.charAt(i) - '0'));
			}
		}

	}

}
