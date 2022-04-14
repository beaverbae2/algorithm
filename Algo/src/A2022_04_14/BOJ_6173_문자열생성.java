package A2022_04_14;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * @author beaverbae
 * @see https://www.acmicpc.net/board/view/70564
 * 
 * - A[s] == A[e] 인 경우, (s, e) 인 범위 내에서 계속 비교 -> 코드 참조  
 */

public class BOJ_6173_문자열생성 {
	static int s, e, N;
	static char[] A;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new char[N];
		s = 0;
		e = N - 1;
		for (int i = 0; i < N; i++) {
			A[i] = br.readLine().charAt(0);
		}
		
		exec();
		System.out.println(sb);
	}
	
	private static void exec() {
		StringBuilder tempSb = new StringBuilder();
		while (s <= e) {
			if (comp(s, e)) tempSb.append(A[s++]);
			else tempSb.append(A[e--]);
			
			if (tempSb.length() == 80) {
				sb.append(tempSb).append("\n");
				tempSb = new StringBuilder();
			}
		}
		
		sb.append(tempSb);
	}

	private static boolean comp(int s, int e) {
		while (s <= e) {
			if (A[s] < A[e]) return true;
			else if (A[s] > A[e]) return false;
			else {
				s++;
				e--;
			}
		}
		
		return true;
	}
}
