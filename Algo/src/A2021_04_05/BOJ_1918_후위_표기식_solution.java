package A2021_04_05;

import java.util.*;
import java.io.*;

/**
 * Stack
 * @author beaverbae
 * @see 예전 코드... 못짜겠다 이건ㅠㅠ
 * 
 */

public class BOJ_1918_후위_표기식_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		Stack<Character> ops = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for (char ch : input) {
			int p = getPriority(ch);
			
			if (p == -1) {// 알파벳인 경우
				sb.append(ch);
			} else {
				if (ch == '(') ops.push(ch);
				else if (ch == ')') {
					while (!ops.isEmpty() && ops.peek() != '(') {
						sb.append(ops.pop());
					}
					ops.pop();// ( 삭제
				} else {// 사직 연산자인 경우
					while (!ops.isEmpty() && getPriority(ops.peek()) >= p) {
						sb.append(ops.pop());
					}
					ops.push(ch);// 연산자 추가
				}
			}
		}
		
		// 남은거 체크
		while (!ops.isEmpty()) {
			sb.append(ops.pop());
		}
		
		System.out.println(sb.toString());
	}
	
	private static int getPriority(char ch) {
		if (ch == '(' || ch == ')') return 1;
		else if (ch == '+' || ch == '-') return 2;
		else if (ch == '*' || ch == '/') return 3;
		return -1;
	}
}
