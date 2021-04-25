package A2021_04_25;

import java.util.*;
import java.io.*;


/**
 * DFS
 * 12MIN
 * @author beaverbae
 *
 */

public class BOJ_14888_연산자_끼워넣기 {
	static int N;
	static int[] nums;
	static int[] ops;
	static int max_sum, min_sum;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		ops = new int[4];
		max_sum = Integer.MIN_VALUE;
		min_sum = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < ops.length; i++) {
			ops[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, nums[0]);
	
		System.out.println(max_sum);
		System.out.println(min_sum);
	}

	private static void dfs(int cnt, int sum) {
		if (cnt == N-1) {  
			max_sum = Math.max(max_sum, sum);
			min_sum = Math.min(min_sum, sum);
			return;
		}
		
		for (int i = 0; i < ops.length; i++) {
			if (ops[i] == 0) continue;
			
			ops[i] -= 1;
			int next_sum = getNextSum(sum, i, cnt+1);
			dfs(cnt+1, next_sum);
			ops[i] += 1;
		}
	}

	private static int getNextSum(int sum, int i, int idx) {
		int result = sum;
		
		if (i == 0) {// +
			result += nums[idx];
		} else if (i == 1) {// -
			result -= nums[idx];
		} else if (i == 2) {// *
			result *= nums[idx];
		} else if (i == 3) {// /
			result /= nums[idx];
		}
		
		return result;
	}
}
