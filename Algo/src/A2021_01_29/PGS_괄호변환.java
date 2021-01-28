package A2021_01_29;

import java.util.*;

/**
 * Simulation 
 * 30MIN
 * @author beaverbae
 *
 */

class Solution {
	public String solution(String p) {
		String answer = dfs(p);
		return answer;
	}

	public String dfs(String s) {
		if (s.length() == 0)
			return s;

		int open = 0, close = 0, v_start = -1;

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(')
				open++;
			else
				close++;

			if (open != 0 && close != 0 && open == close) {
				v_start = i + 1;
				break;
			}
		}
		String u = s.substring(0, v_start);
		String v = s.substring(v_start);

		if (check(u)) {
			return u + dfs(v);
		} else {
			String result = "(" + dfs(v) + ")";
			u = u.substring(1, u.length() - 1);
			String reverse_u = "";

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < u.length(); i++) {
				char ch = u.charAt(i);
				if (ch == '(') {
					sb.append(')');
				} else {
					sb.append('(');
				}
			}
			reverse_u = sb.toString();
			result += reverse_u;
			return result;
		}
	}

	private boolean check(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == '(') {
				stack.push(ch);
			} else {
				if (stack.isEmpty())
					return false;
				else
					stack.pop();
			}
		}

		return true;
	}
}
