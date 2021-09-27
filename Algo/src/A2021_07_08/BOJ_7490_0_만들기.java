package A2021_07_08;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 54MIN
 * 어려웠던 부분
 * - ' '처리
 * @author beaverbae
 *
 */

public class BOJ_7490_0_만들기 {
	static StringBuilder sb;
	static int N;
	static int[] nums;
	static char[] ops = {' ', '+', '-'};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			for (int j = 0; j < N; j++) {
				nums[j] = j+1;
			}
			dfs(Integer.toString(nums[0]), 1);
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}


	private static void dfs(String exp, int cnt) {
		if (cnt == N) {
			if (getSum(exp) == 0) {
				sb.append(exp).append("\n");
			}
			return;
		}
		
		for (int i = 0; i < ops.length; i++) {
			String next_exp = exp + ops[i] + nums[cnt];
			dfs(next_exp, cnt+1);
		}
	}


	private static int getSum(String exp) {
		exp = exp.replaceAll(" ", "");
		ArrayList<Integer> nums = new ArrayList<>();
		ArrayList<Character> ops = new ArrayList<>();
		
		String num = "";
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			
			if (ch == '+' || ch == '-') {
				nums.add(Integer.parseInt(num));
				num = "";
				ops.add(ch);
			} else {
				num += ch;
			}
		}
		nums.add(Integer.parseInt(num));
		
		int sum = nums.get(0);
		for (int i = 0; i < ops.size(); i++) {
			if (ops.get(i) == '+') {
				sum += nums.get(i+1);
			} else {
				sum -= nums.get(i+1);
			}
		}
		
		return sum;
	}
}
