package A2021_04_05;

import java.util.*;
import java.io.*;

/**
 * Stack
 * @author beaverbae
 *  
 */

public class BOJ_1935_후위_표기식2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] input = br.readLine().toCharArray();
		HashMap<Character, String> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			char ch = (char) ('A'+i);
			map.put(ch, br.readLine());
		}
		
		LinkedList<String> expression = new LinkedList<>();
		
		for (int i = 0; i < input.length; i++) {
			char ch = input[i];
			if (map.containsKey(ch)) {
				expression.add(map.get(ch));
			} else {
				expression.add(ch+"");
			}
		}
		
		Stack<Double> stack = new Stack<>();
		for (String s : expression) {
			if (s.equals("+")) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(a+b);
			} else if (s.equals("-")) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(a-b);
			} else if (s.equals("*")) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(a*b);
			} else if (s.equals("/")) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(a/b);
			} else {
				stack.push(Double.parseDouble(s));
			}
		}
		
		System.out.println(String.format("%.2f", stack.pop()));
	}
}
