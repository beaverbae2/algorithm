package A2021_07_08;

import java.util.*;
import java.io.*;

/**
 * DFS(subset)
 * 어려웠던 부분
 * - 처음에 조합으로 접근 -> 괄호가 있을수도 없을 수도 있으므로 조합 불가...
 * - 괄호를 만들어서 연산 -> 그 다음 연산에 괄호가 없느냐 있느냐로 구분
 * @author beaverbae
 *
 */

public class BOJ_16637_괄호_추가하기_solution {
	static int N;
	static int[] nums;
	static char[] ops;
	static boolean[] isParentheis;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N/2+1];
		ops = new char[N/2];
		isParentheis = new boolean[N/2];
		answer = Integer.MIN_VALUE;
		
		char[] input = br.readLine().toCharArray();
		for (int i = 0; i < input.length; i++) {
			if (i % 2 == 0) {
				nums[i/2] = input[i] - '0';
			} else {
				ops[i/2] = input[i];
			}
		}
		
		dfs(nums[0], 0);
		System.out.println(answer);
	}

	private static void dfs(int sum, int cnt) {
		if (cnt >= N/2) {
			answer = Math.max(answer, sum);
			return; 
		}
		
		// 다음 연산에 괄호가 없는 경우
		int next_sum = calc(sum, nums[cnt+1], ops[cnt]);
		dfs(next_sum, cnt+1);
		
		// 다음 연산에 괄호가 있는 경우
		if (cnt + 1 < N/2) {
			int right = calc(nums[cnt+1], nums[cnt+2], ops[cnt+1]);
			next_sum = calc(sum, right, ops[cnt]);
			dfs(next_sum, cnt+2);// 다다음으로..
		}
	}

	
	private static int calc(int a, int b, char op) {
		if (op == '+') {
			return a+b;
		} else if (op == '-') {
			return a-b;
		} else if (op == '*') {
			return a*b;
		}
		
		return 0;
	}
}
