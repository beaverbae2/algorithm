package A2022_03_22;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * 목표 채널로 가는 방법
 * 1. 리모컨에 숫자 입력 + 위/아래 -> 자리수-1, 자리수, 자리수+1 모두 탐색 필요
 * 2. 위/아래 -> 굳이 BFS 쓸 필요없음
 * 
 */


public class BOJ_1107_리모컨 {
	static int target, N, sub, min;
	static int start = 100;
	static boolean[] notUse;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		target = Integer.parseInt(s);
		N = s.length();
		notUse = new boolean[10];
		int M = Integer.parseInt(br.readLine());
		
		if (M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (M-- > 0) {
				notUse[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		System.out.println(exec());
	}

	private static int exec() {
		int s1 = 987654321, s2 = 0;
		for (int i = -1; i < 2; i++) {
			sub = 987654321;
			min = sub;
			if (N + i <= 0) continue;
			dfs(0, 0, N+i);
			s1 = Math.min(s1, N+i + Math.abs(min - target));
		}
		
		s2 = Math.abs(start - target);
		
		return s1 < s2 ? s1 : s2;
	}
	
	private static void dfs(int num, int idx, int N) {
		if (idx == N) {
			if (sub > Math.abs(num - target)) {
				sub = Math.abs(num - target);
				min = num;
			}
			
			return;
		}
		
		for (int n = 0; n <= 9; n++) {
			if (notUse[n]) continue;
			
			dfs(10 * num + n, idx + 1, N);
		}
	}
}
