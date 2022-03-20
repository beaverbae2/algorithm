package A2022_03_20;

import java.util.*;
import java.io.*;

/**
 * Divide and Conquer
 * 67MIN
 * @author beaverbae
 * - 최적화 필요 -> 배열 잡고 푸는게 아님 (항상 최대인 케이스 생각하자)
 * - 값의 범위 : 재귀 탐색시, 해당 범위에 대입할 수 있는 값도 범위가 존재
 * 
 */

public class BOJ_1074_Z {
	static int N, R, C, ans = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		calc(0, N, 0, N, 0, N*N, N*N/4);
		
		System.out.println(ans);
	}

	private static void calc(int sr, int er, int sc, int ec, int min, int max, int d) {
		if (ans != -1) return;
		
		if (d == 0) {
			ans = max-1;
			return;
		}
		
		if (R < (sr+er)/2) {
			if (C < (sc+ec)/2) calc(sr, (sr+er)/2, sc, (sc+ec)/2, min, min + d, d/4);
			else calc(sr, (sr+er)/2, (sc+ec)/2, ec, min + d, min + 2*d, d/4);
		} else {
			if (C < (sc+ec)/2) calc((sr+er)/2, er, sc, (sc+ec)/2, min + 2*d, min + 3*d, d/4);
			else calc((sr+er)/2, er, (sc+ec)/2, ec, min + 3*d, max, d/4);
		}
	}
}
