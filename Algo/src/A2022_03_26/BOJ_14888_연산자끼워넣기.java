package A2022_03_26;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 14MIN
 * @author beaverbae
 *
 */

public class BOJ_14888_연산자끼워넣기 {
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] nums;
	static int[] OpCnts = new int[4];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < OpCnts.length; i++) {
			OpCnts[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1, nums[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	private static void dfs(int idx, int sum) {
		if (idx == N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for (int i = 0; i < OpCnts.length; i++) {
			if (OpCnts[i] == 0) continue;
			
			OpCnts[i]--;
			dfs(idx+1, calc(sum, nums[idx], i));
			OpCnts[i]++;
		}
	}
	
	private static int calc(int a, int b, int op) {
		switch (op) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		default:
			return a / b;
		}
	}
}
