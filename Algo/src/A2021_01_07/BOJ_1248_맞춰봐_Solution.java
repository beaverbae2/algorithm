package A2021_01_07;

import java.util.*;
import java.io.*;

/**
 * 
 * @author beaverbae
 * @see https://toastfactory.tistory.com/206
 *
 */

//무식하게 백트레킹 해도 시간초과가 안나네....
public class BOJ_1248_맞춰봐_Solution {
	static int[] A;
	static char[][] S;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new char[N][N];
		A = new int[N];
		char[] charArr = br.readLine().toCharArray();
		
		int idx = 0;
		for (int i = 0; i < S.length; i++) {
			for (int j = i; j < S[i].length; j++) {
				S[i][j] = charArr[idx];
				idx++;
			}
		}
		
		dfs(0);
		
	}

	private static void dfs(int cnt) {
		if (cnt == N) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < A.length; i++) {
				sb.append(A[i]).append(" ");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		for (int i = -10; i <= 10; i++) {
			A[cnt] = i;
			if (check(cnt)) dfs(cnt+1);
		}
	}

	private static boolean check(int end) {
		for (int i = 0; i <= end; i++) {
			int sum = 0;
			for (int j = i; j <= end; j++) {
				sum += A[j];
				if (sum == 0) {
					if(S[i][j] == '+' || S[i][j] == '-') return false;
				}else if(sum > 0) {
					if(S[i][j] == '0' || S[i][j] == '-') return false;
				}else if(sum < 0) {
					if(S[i][j] == '+' || S[i][j] == '0') return false;
				}
			}
		}
		
		return true;
	}
}
