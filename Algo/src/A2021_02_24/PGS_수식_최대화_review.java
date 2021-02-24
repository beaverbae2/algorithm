package A2021_02_24;

import java.util.*;

/**
 * Stack(후위표기식)
 * 
 * @author beaverbae
 * @see https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=220654116881&proxyReferer=https:%2F%2Fwww.google.com%2F
 *
 */

public class PGS_수식_최대화_review {
	char[] op = { '+', '*', '-' };
	HashMap<Character, Integer> opOrder = new HashMap<>();
	boolean[] visited = new boolean[3];
	long answer;
	String expression;

	public long solution(String expression) {
		this.expression = expression;
		permutation(0);
		return answer;
	}

	public void permutation(int cnt) {
		if (cnt == op.length) {
			List<String> postExpression = getPostExpression();// 후위표기식으로 변환
			long result = caculate(postExpression);// 계산
			answer = Math.max(answer, result);
			return;
		}

		for (int i = 0; i < op.length; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			opOrder.put(op[i], cnt);
			permutation(cnt + 1);
			visited[i] = false;
		}
	}

	public long caculate(List<String> postExpression) {
		Stack<String> stack = new Stack<>();

		for (String s : postExpression) {
			if (s.equals("+")) {
				long b = Long.parseLong(stack.pop());
				long a = Long.parseLong(stack.pop());
				stack.push(Long.toString((a + b)));
			} else if (s.equals("-")) {
				long b = Long.parseLong(stack.pop());
				long a = Long.parseLong(stack.pop());
				stack.push(Long.toString((a - b)));
			} else if (s.equals("*")) {
				long b = Long.parseLong(stack.pop());
				long a = Long.parseLong(stack.pop());
				stack.push(Long.toString((a * b)));
			} else {
				stack.push(s);
			}
		}

		long result = Long.parseLong(stack.pop());
		return Math.abs(result);
	}

	public List<String> getPostExpression() {
		List<String> postExpression = new ArrayList<>();
		Stack<Character> opStack = new Stack<>();

		String num = "";
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (isNumber(ch)) {
				num += ch;
			} else {
				postExpression.add(num);
				while (true) {
					if (opStack.isEmpty()) {// 스택이 빈 경우 연산자 push
						opStack.push(ch);
						break;
					} else {
						char top = opStack.peek();
						if (isPriorOp(ch, top)) {// 스택의 최상위 연산자보다 현재 연산자의 우선순위가 높을때
							opStack.push(ch);
							break;
						} else {
							postExpression.add(opStack.pop() + "");
						}
					}
				}

				num = "";
			}
		}
		postExpression.add(num);// 남아 있는 숫자 추가

		// 남아 있는 연산자 추가
		while (!opStack.isEmpty()) {
			postExpression.add(opStack.pop() + "");
		}

		return postExpression;
	}

	public boolean isPriorOp(char ch, char top) {
		return opOrder.get(ch) < opOrder.get(top);
	}

	public boolean isNumber(char ch) {
		return ch >= '0' && ch <= '9';
	}

	public static void main(String[] args) {
		System.out.println(new PGS_수식_최대화_review().solution("100-200*300-500+20"));
		System.out.println(new PGS_수식_최대화_review().solution("50*6-3*2"));
	}
}
