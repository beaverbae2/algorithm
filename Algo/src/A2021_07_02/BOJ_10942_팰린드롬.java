package A2021_07_02;

import java.util.*;
import java.io.*;

/**
 * Memoization
 * 20MIN
 * @author beaverbae
 * 
 * 구현 방법
 * - 구간 [s, e]가 팰린드롬이면 [s-1, e-1], [s-2, e-2], ... [x, x]도 팰린드롬임을 활용 
 */

public class BOJ_10942_팰린드롬 {
	static int N, M;
	static int[] arr;
	static boolean[][] isPalindrome;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		isPalindrome = new boolean[N][N];
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
		
			if (!isPalindrome[s][e]) checkPalindrome(s, e);
			if (isPalindrome[s][e]) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	private static void checkPalindrome(int s, int e) {
		boolean result = true;
		Queue<int[]> q = new LinkedList<>();
		
		while (s <= e) {
			if (arr[s] != arr[e]) {
				result = false;
				break;
			} else {
				q.offer(new int[] {s, e});
			}
			s++;
			e--;
		}
		
		if (result) {
			while (!q.isEmpty()) {
				int[] pair = q.poll();
				isPalindrome[pair[0]][pair[1]] = true;
			}
		}
	}
}
