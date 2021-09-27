package A2021_07_09;

import java.util.*;
import java.io.*;

/**
 * DFS(subset)
 * 주의할 부분
 * - 공집합은 존재하지 않는다
 * @author beaverbae
 *
 */

public class BOJ_1182_부분수열의_합 {
	static int N, S;
	static int[] arr;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, 0);
		System.out.println(ans);
	}
	
	private static void dfs(int idx, int cnt, int sum) {
		if (idx == N) {
			if (cnt > 0 && sum == S) {
				ans++;
			}
			return;
		}
		
		dfs(idx+1, cnt+1, sum + arr[idx]);
		dfs(idx+1, cnt, sum);
	}
}
