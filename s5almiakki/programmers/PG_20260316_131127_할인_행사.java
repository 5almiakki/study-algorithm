import java.util.*;

public class PG_20260316_131127_할인_행사 {

    class Solution {

        public int solution(String[] want, int[] number, String[] discount) {
            Map<String, Integer> wishList = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                wishList.put(want[i], number[i]);
            }
            Window wishListWindow = new Window(wishList);
            // System.out.println("wishList=" + wishListWindow);

            Window window = new Window();
            for (int i = 0; i < 9; i++) {
                window.add(discount[i]);
            }

            int answer = 0;
            for (int i = 9; i < discount.length; i++) {
                window.add(discount[i]);
                // System.out.println("window=" + window);
                if (window.equals(wishListWindow)) {
                    answer++;
                }
                window.remove(discount[i - 9]);
            }

            return answer;
        }

        static class Window {

            private final Map<String, Integer> window;

            Window() {
                window = new HashMap<>();
            }

            Window(Map<String, Integer> window) {
                this.window = new HashMap<>(window);
            }

            public void add(String key) {
                window.put(key, window.getOrDefault(key, 0) + 1);
            }

            public void remove(String key) {
                Integer value = window.get(key);
                if (value == null) {
                    return;
                }
                if (value == 1) {
                    window.remove(key);
                    return;
                }
                window.put(key, value - 1);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Window)) {
                    return false;
                }
                Window other = (Window) o;
                return window.equals(other.window);
            }

            @Override
            public int hashCode() {
                return window.hashCode();
            }

            @Override
            public String toString() {
                return window.toString();
            }

        }

    }

}
