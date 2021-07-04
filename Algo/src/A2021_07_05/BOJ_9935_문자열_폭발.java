package A2021_07_05;

import java.util.*;
import java.io.*;

/**
 * Stack
 * 주말에 다시 풀어보자
 * @author beaverbae
 *
 */

public class BOJ_9935_문자열_폭발 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		char[] bomb = br.readLine().toCharArray();
		
		int i_idx = 0;
		int b_idx = bomb.length - 1;
		Stack<Character> stack = new Stack<>();
		Stack<Character> subStack = new Stack<>();
		
		while (i_idx < input.length) {
			stack.push(input[i_idx++]);
			
			while (!stack.isEmpty() && stack.peek() == bomb[b_idx]) {
				subStack.push(stack.pop());

				if (b_idx == 0) {
					subStack = new Stack<>();
					break;
				} else {
					b_idx--;
				}
			}
			
			while (!subStack.isEmpty()) {
				stack.push(subStack.pop());
			}
			
			b_idx = bomb.length - 1;
		}
		
		StringBuilder sb = new StringBuilder();
		for (char ch : stack) {
			sb.append(ch);
		}
		
		if (sb.length() == 0) {
			sb.append("FRULA");
		}
		
		System.out.println(sb.toString());
	}
}
