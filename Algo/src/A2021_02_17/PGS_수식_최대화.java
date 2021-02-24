package A2021_02_17;

import java.util.*;

/**
 * Simulation
 * 
 * @author beaverbae
 *
 */

public class PGS_수식_최대화 {
	char[] op = { '*', '-', '+' };
	char[] selected_op = new char[3];
	boolean[] visited = new boolean[3];
	long answer = 0;
	boolean minus;

	public long solution(String expression) {
		permutation(0, expression);
		System.out.println(answer);
		return answer;
	}

	public void permutation(int cnt, String expression) {
		if (cnt == 3) {
			answer = Math.max(answer, calculate(expression));

			return;
		}

		for (int i = 0; i < op.length; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			selected_op[cnt] = op[i];
			permutation(cnt + 1, expression);
			visited[i] = false;
		}
	}
	
	// 우선 순위 대로 계산
	public long calculate(String expression) {
		minus = false;// -가 연산자 : false, -가 숫자앞에 붙는 경우 : true
		for (int i = 0; i < selected_op.length; i++) {
			String next = "";
			String first = "";
			String second = "";

			boolean flag = false;// 목표연산자(selected_op[i])가 등장했는가
			for (int j = 0; j < expression.length(); j++) {
				char ch = expression.charAt(j);

				int checkResult = check(ch, selected_op[i]);

				if (checkResult == 0) {// 사용할 연산자
					if (flag) {
						long a = Long.parseLong(first);
						long b = Long.parseLong(second);
						long result = calc(a, b, selected_op[i]);

						first = Long.toString(result);
						second = "";
					}
					flag = true;
				}

				else if (checkResult == 1) {// 사용할 연산자가 아닌 다른 연산자
					if (flag) {
						long a = Long.parseLong(first);
						long b = Long.parseLong(second);
						long result = calc(a, b, selected_op[i]);

						first = Long.toString(result);
					}

					flag = false;
					next += first + ch;
					first = "";
					second = "";
				}

				else if (checkResult == 2) {// 숫자인 경우
					if (flag) {
						second += ch;
						if (j == expression.length() - 1) {// 끝인 경우
							long a = Long.parseLong(first);
							long b = Long.parseLong(second);
							long result = calc(a, b, selected_op[i]);

							first = Long.toString(result);
						}

					} else {
						first += ch;
					}
				}
			}
			next += first;
			expression = next;
			if (selected_op[i] == '-') {
				minus = true;
			}
		}

		return Math.abs(Long.parseLong(expression));
	}

	public int check(char ch, char target_op) {
		if (ch >= '0' && ch <= '9') // 숫자인 경우
			return 2; 
		else if (ch != target_op) {// 사용할 연산자가 아닌경우우
			if (ch == '-') {
				if (minus) {// -가 붙은 숫자인 경우
					return 2;
				} else// -가 연산자 인경우
					return 1;
			}
			return 1;// -를 제외한 다른 연산자(+, *)
		}
		return 0;// 사용할 연산자인 경우
	}

	public long calc(long a, long b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '-') {
			return a - b;
		}
		return a * b;
	}

	public static void main(String[] args) {
		new PGS_수식_최대화().solution("100-200*300-500+20");
		new PGS_수식_최대화().solution("50*6-3*2");
		new PGS_수식_최대화().solution("100-200-300");
	}
}
