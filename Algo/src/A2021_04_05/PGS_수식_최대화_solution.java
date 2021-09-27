package A2021_04_05;

import java.util.*;
/**
 * Stack
 * @author beaverbae
 * @see https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=220654116881&proxyReferer=https:%2F%2Fwww.google.com%2F
 *
 */

public class PGS_수식_최대화_solution {
	String[] op = {"*", "+", "-"};
	HashMap<String, Integer> priority = new HashMap<>();
	LinkedList<String> exp_list = new LinkedList<>();
	long answer;
	
	public long solution(String expression) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (isNumber(ch)) sb.append(ch);
			else {
				exp_list.add(sb.toString());
				exp_list.add(ch+"");
				sb = new StringBuilder();
			}
		}
		exp_list.add(sb.toString());
		calcPriority(new boolean[3], 0);
		
		return answer;
	}
	
	private void calcPriority(boolean[] visited, int idx) {
		if (idx == 3) {
			getPostExpression();
			return;
		}
		
		for (int p = 0; p < 3 ; p++) {
			if (visited[p]) continue;
			
			visited[p] = true;
			priority.put(op[p], idx);
			calcPriority(visited, idx+1);
			visited[p] = false;
		}
	}

	private void getPostExpression() {
		LinkedList<String> post_expression = new LinkedList<String>();
		Stack<String> ops = new Stack<>();
		for (String s : exp_list) {
			if (!priority.containsKey(s)) {
				post_expression.add(s);
			} else {
				int p = priority.get(s);
				
				while (!ops.isEmpty()) {
					int peek_p = priority.get(ops.peek());
					if (peek_p >= p) {
						post_expression.add(ops.pop());
					} else {
						break;
					}
				}
				ops.push(s);
			}
		}
		
		while (!ops.isEmpty()) {
			post_expression.add(ops.pop());
		}
		calcPostExpression(post_expression);
	}
	
	private void calcPostExpression(LinkedList<String> post_expression) {
		Stack<String> stack = new Stack<>();
		for (String s : post_expression) {
			if (s.equals("+")) {
				long b = Long.parseLong(stack.pop());
				long a = Long.parseLong(stack.pop());
				stack.push(Long.toString((a+b)));
			} else if (s.equals("-")) {
				long b = Long.parseLong(stack.pop());
				long a = Long.parseLong(stack.pop());
				stack.push(Long.toString((a-b)));
			} else if (s.equals("*")) {
				long b = Long.parseLong(stack.pop());
				long a = Long.parseLong(stack.pop());
				stack.push(Long.toString((a*b)));
			} else {
				stack.push(s);
			}
		}
		answer = Math.max(answer, Math.abs(Long.parseLong(stack.pop())));
	}

	private boolean isNumber(char ch) {
		return ch>='0' && ch<='9';
	}
	

	public static void main(String[] args) {
		new PGS_수식_최대화_solution().solution("100-200*300-500+20");
		new PGS_수식_최대화_solution().solution("50*6-3*2");
	}
}
