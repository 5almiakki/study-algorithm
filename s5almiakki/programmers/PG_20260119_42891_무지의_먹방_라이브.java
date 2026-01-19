import java.util.*;

public class PG_20260119_42891_무지의_먹방_라이브 {

    class Solution {

        public int solution(int[] food_times, long k) {
            long sum = 0L;
            List<Dish> dishes = new ArrayList<>();
            for (int i = 0; i < food_times.length; i++) {
                sum += (long) food_times[i];
                dishes.add(new Dish(i + 1, food_times[i]));
            }

            // 다 먹고 나서 먹방이 멈춘 경우
            if (sum <= k) {
                return -1;
            }

            // minAmountSum = 음식 수 * (minAmount = amount 값들 중 최솟값)
            // k에서 minAmountSum을 뺄 수 없을 때까지 k에서 minAmountSum을 뺌
            // 그리고 모든 음식의 amount에서 minAmount를 뺌
            int prevMinAmount = 0;
            dishes.sort((o1, o2) -> Integer.compare(o2.amount, o1.amount));
            do {
                int dishCount = dishes.size();
                Dish minAmountDish = dishes.get(dishCount - 1);
                int deltaAmount = minAmountDish.amount - prevMinAmount;
                long minAmountSum = (long) deltaAmount * (long) dishCount;
                if (k < minAmountSum) {
                    break;
                }
                k -= minAmountSum;
                prevMinAmount = minAmountDish.amount;
                dishes.remove(dishCount - 1);
            } while (true);

            int answer = -1;
            k %= (long) dishes.size();
            dishes.sort((o1, o2) -> Integer.compare(o1.food, o2.food));
            for (Dish d : dishes) {
                if (k == 0L) {
                    answer = d.food;
                    break;
                }
                k--;
            }
            return answer;
        }

        private static class Dish {

            public int food;
            public int amount;

            public Dish(int food, int amount) {
                this.food = food;
                this.amount = amount;
            }

        }

    }

}
