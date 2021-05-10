package A2021_05_10;

import java.util.*;
import java.io.*;

/**
 * Divide and Conquer
 * 어려웠던 이유
 * - 메모리 최적화 : 2차원 배열 사용 불가
 * - 시간 최적화 : 재귀 시 탐색 범위에 (r, c) 가 포함된 함수만 호출
 * - 반드시 다시 풀어볼 것  
 * @author beaverbae
 *
 */

public class BOJ_1074_Z {
	static int cnt;
	static int N, r, c;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		N = (int) (Math.pow(2, N));
		answer = -1;
		dfs(0, 0, N-1, N-1, 0, N*N);
		System.out.println(answer);
	}

	private static void dfs(int sr, int sc, int er, int ec, int start, int end) {
		if (answer != -1) return;
		
		// conquer
		if (start+1 == end) {
			answer = start;
			return;
		}

		// divide
		int n = (end - start)/4;
		int start1 = start;
		int end1 = start1 + n;
		
		if (r >= sr && r <=(sr+er)/2 && c >= sc && c <= (sc+ec)/2) {
			dfs(sr, sc, (sr+er)/2, (sc+ec)/2, start1, end1);
		}
		
		int start2 = end1;
		int end2 = start2 + n;
		
		if (r >= sr && r <=(sr+er)/2 && c >= (sc+ec)/2+1 && c <= ec) {
			dfs(sr, (sc+ec)/2+1, (sr+er)/2, ec, start2, end2);
		}
		
		int start3 = end2;
		int end3 = start3 + n;
		
		if (r >= (sr+er)/2+1 && r <= er && c >= sc && c <= (sc+ec)/2) {
			dfs((sr+er)/2+1, sc, er, (sc+ec)/2, start3, end3);
		}
		
		int start4 = end3;
		int end4 = start4 + n;
		
		if (r >= (sr+er)/2+1 && r <= er && c >= (sc+ec)/2+1 && c <= ec) {
			dfs((sr+er)/2+1, (sc+ec)/2+1, er, ec, start4, end4);
		}
	}
}
