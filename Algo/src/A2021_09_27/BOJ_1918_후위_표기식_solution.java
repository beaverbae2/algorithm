package A2021_09_27;

import java.util.*;
import java.io.*;

/**
 * Stack
 * @author beaverbae
 * 핵심은 연산자 우선 순위
 * 현재 연산자가 stack의 top에 있는 연산자 보다 우선순위가 작으면 top에 있는 연산자를 후위표기식에 계속 추가
 * 피연산자도 stack을 쓰려 했으나(까다로웠다) 그럴 필요없이 순서대로 후위표기식에 추가해주면 됐다
 * 마지막에 stack에 남아있는 연산자가 있을 수 있다
 */

public class BOJ_1918_후위_표기식_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for (char ch : arr) {
			int p = priority(ch);
			
			if (p == -1) sb.append(ch);
			else {
				if (ch == ')') {
					while (!stack.isEmpty()) {
						if (stack.peek() == '(') {
							stack.pop();
							break;
						}
						sb.append(stack.pop());
					}
				} else if (ch == '(') {
					stack.push(ch);
				} else { // 사칙 연산자
					while (!stack.isEmpty()) {
						if (p > priority(stack.peek())) break;
						sb.append(stack.pop());
					}
					stack.push(ch);
				}
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
	}
	
	private static int priority(char ch) {
		switch (ch) {
			case '*':
				return 2;
			case '/':
				return 2;
			case '+':
				return 1;
			case '-':
				return 1;
			case '(':
				return 0;
			case ')':
				return 0;
			default:
				return -1;
		}
	}
	
	private static boolean isOp(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}
}
