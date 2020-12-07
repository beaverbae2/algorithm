package A2020_12_07;

import java.util.*;

public class PGS_전화번호목록 {
	public static void main(String[] args) throws Exception {
		System.out.println(solution(new String[] { "119", "97674223", "1195524421]" }));
		System.out.println(solution(new String[] { "123", "456", "789" }));
		System.out.println(solution(new String[] { "12", "123", "1235", "567", "88" }));

	}

	public static boolean solution(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		HashMap<String, Boolean> prefix = new HashMap<String, Boolean>();
		prefix.put(phone_book[0], true);

		for (int i = 1; i < phone_book.length; i++) {
			String phone_num = phone_book[i];
			String str = "";
			for (int j = 0; j < phone_num.length(); j++) {
				str += phone_num.charAt(j);
				if (prefix.get(str) == null)
					continue;
				else if (prefix.get(str)) {
					answer = false;
					break;
				}
			}
			if (!answer)
				break;
			else
				prefix.put(str, true);
		}

		return answer;
	}
}
