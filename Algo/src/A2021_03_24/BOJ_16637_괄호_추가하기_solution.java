package A2021_03_24;

import java.util.*;
import java.io.*;

/**
 * 
 * @author beaverbae
 * @see https://steady-coding.tistory.com/36
 *
 */

public class BOJ_16637_괄호_추가하기_solution {
	static int answer, N;
	static ArrayList<Character> ops;
	static ArrayList<Integer> nums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		
		ops = new ArrayList<>();
		nums = new ArrayList<>();
		answer = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			char ch = input.charAt(i);
			
			if (ch == '+' || ch == '-' || ch == '*') {
				ops.add(ch);
			} else {
				nums.add(ch-'0');
			}
		}
		
		dfs(nums.get(0),0);
		System.out.println(answer);
	}
	
	private static int calc(char op, int n1, int n2) {
		if (op == '+') {
			return n1+n2;
		} else if (op == '-') {
			return n1-n2;
		} else {
			return n1*n2;
		}
	}

	private static void dfs(int sum, int idx) {
		if (idx >= ops.size()) {
			answer = Math.max(answer, sum);
			return;
		}
		
		// 오른쪽에 괄호가 없는 경우
		int next_sum = calc(ops.get(idx), sum, nums.get(idx+1));// 그대로 연산
		dfs(next_sum, idx+1);
		
		// 오른쪽에 괄호가 있는 경우
		if (idx+1 < ops.size()) {
			int right = calc(ops.get(idx+1), nums.get(idx+1),nums.get(idx+2));// 오른쪽 값 연산
			next_sum = calc(ops.get(idx), sum, right);// 현재값과 오른쪽값 연산
			dfs(next_sum, idx+2);
		}
	}
}
