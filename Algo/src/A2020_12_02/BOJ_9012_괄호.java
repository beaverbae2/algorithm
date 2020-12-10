package A2020_12_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9012_괄호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < TC; t++) {
			Stack<Character> stack = new Stack<>();
			String ps = br.readLine();
			boolean flag = true;
			
			for (int i = 0; i < ps.length(); i++) {
				char ch = ps.charAt(i);
				if(ch=='(') {
					stack.push(ch);
				}else {
					if(!stack.isEmpty()) stack.pop();
					else {
						flag = false;
						break;
					}
				}
			}
			if(!flag||!stack.isEmpty()) sb.append("NO").append("\n");
			else sb.append("YES").append("\n");
		}
		System.out.println(sb);
	}
}
