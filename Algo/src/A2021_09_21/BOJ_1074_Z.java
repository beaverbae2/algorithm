package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * 48MIN
 * Divide and Conquer
 * @author beaverbae
 * 분할정복에서의 최적화
 */

public class BOJ_1074_Z {
	static int ans = 0;
	static int R, C;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		dfs(N, 0, 0);
		System.out.println(ans);
	}

	private static void dfs(int n, int sr, int sc) {
		if (flag) return;
		int next = (int) Math.pow(2, n-1);
		if (n == 1) {
			for (int r = sr; r <= sr + next; r++) {
				for (int c = sc; c <= sc + next; c++) {
					if (r == R && c == C) {
						flag = true;
						return;
					} else ans++;
				}
			}
			return;
		}
		
		if (check(next, sr, sc)) dfs(n-1, sr, sc);
		else if (check(next, sr, sc+next)) {
			ans += next * next;
			dfs(n-1, sr, sc+next);
		} else if (check(next, sr+next, sc)) {
			ans += next * next * 2;
			dfs(n-1, sr+next, sc);
		} else if (check(next, sr+next, sc+next)) {
			ans += next * next * 3;
			dfs(n-1, sr+next, sc+next);
		}
	}

	private static boolean check(int n, int sr, int sc) {
		if ((R >= sr && R < sr + n) && C >= sc && C < sc + n) return true;
		return false;
	}
	
	
}
