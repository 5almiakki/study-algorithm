import java.util.*;

public class PG_20260331_77486_다단계_칫솔_판매 {

    class Solution {

        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            Map<String, Integer> memberToIdxMap = new HashMap<>();
            memberToIdxMap.put("-", -1);
            for (int i = 0; i < enroll.length; i++) {
                memberToIdxMap.put(enroll[i], i);
            }
            int[] memberToReferralMap = new int[enroll.length];
            for (int i = 0; i < enroll.length; i++) {
                memberToReferralMap[memberToIdxMap.get(enroll[i])] = memberToIdxMap.get(referral[i]);
            }

            int[] answer = new int[enroll.length];
            for (int i = 0; i < seller.length; i++) {
                int member = memberToIdxMap.get(seller[i]);
                int income = amount[i] * 100;
                while (member != -1 && income > 0) {
                    int parent = memberToReferralMap[member];
                    int parentIncome = income / 10;
                    answer[member] += income - parentIncome;
                    member = parent;
                    income = parentIncome;
                }
            }

            return answer;
        }

    }

}
