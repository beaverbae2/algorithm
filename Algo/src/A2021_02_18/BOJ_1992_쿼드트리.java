package A2021_02_18;

import java.io.*;

public class BOJ_1992_쿼드트리 {
	static char[][] input;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				input[i][j] = str.charAt(j);
			}
		}
		
		System.out.println(getQuad(N,0, 0, N, N));
	}
	
	static String getQuad(int n, int sr, int sc, int dr, int dc) {
		char ch = input[sr][sc];
		
		if (isAllSame(ch, sr, sc, dr, dc)) {
			return ch+"";
		}
		
		// 종료 조건
		if (n==2) {
			String result = "(";
			for (int i = sr; i < dr; i++) {
				for (int j = sc; j < dc; j++) {
					result += input[i][j];
				}
			}
			result += ")";
			return result;
		}
		
		// 반복
		String result = "(";
		int mr = (sr+dr)/2;
		int mc = (sc+dc)/2;
		
		result += getQuad(n/2, sr, sc, mr, mc);
		result += getQuad(n/2, sr, mc, mr, dc);
		result += getQuad(n/2, mr, sc, dr, mc);
		result += getQuad(n/2, mr, mc, dr, dc);
		
		result += ")";
		return result;
	}
	
	static boolean isAllSame(char ch, int sr, int sc, int dr, int dc) {
		for (int i = sr; i < dr; i++) {
			for (int j = sc; j < dc; j++) {
				if (input[i][j] != ch) return false;
			}
		}
		return true;
	}
}
