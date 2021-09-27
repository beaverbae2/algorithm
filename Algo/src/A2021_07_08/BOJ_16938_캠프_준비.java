package A2021_07_08;

import java.util.*;
import java.io.*;

/**
 * DFS(subset)
 * 13 MIN
 * @author beave
 *
 */

public class BOJ_16938_캠프_준비 {
	static int N, L, R, X;
	static int[] arr;
	static int ans;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, 0, INF, 0);
		
		System.out.println(ans);
	}

	private static void dfs(int idx, int sum, int cnt, int easy, int hard) {
		if (idx == N) {
			if (sum >= L && sum <=R && cnt >= 2 && hard - easy >= X) {
				ans++;
			}
			return;
		}
		
		// 선택
		dfs(idx+1, sum+arr[idx], cnt+1, Math.min(easy, arr[idx]), Math.max(hard, arr[idx]));
		// 선택X
		dfs(idx+1, sum, cnt, easy, hard);
	}
}
