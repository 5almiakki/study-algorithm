import java.util.*;

public class PG_20260409_42577_전화번호_목록 {

    class Solution1 {

        public boolean solution(String[] phone_book) {
            String[] phoneNums = Arrays.copyOf(phone_book, phone_book.length);
            Arrays.sort(phoneNums);
            for (int i = 1; i < phoneNums.length; i++) {
                if (phoneNums[i].startsWith(phoneNums[i - 1])) {
                    return false;
                }
            }
            return true;
        }

    }

    class Solution2 {

        public boolean solution(String[] phone_book) {
            Set<String> phoneNums = new HashSet<>();
            for (String phoneNum : phone_book) {
                phoneNums.add(phoneNum);
            }
            for (String phoneNum : phone_book) {
                StringBuilder sb = new StringBuilder();
                int length = phoneNum.length();
                for (int i = 0; i < length - 1; i++) {
                    sb.append(phoneNum.charAt(i));
                    if (phoneNums.contains(sb.toString())) {
                        return false;
                    }
                }
            }
            return true;
        }

    }

}
