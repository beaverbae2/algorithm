package A2021_10_03;

import java.util.*;
import java.io.*;

/**
 * 26MIN
 * Simulation
 * @author beaverbae
 * 운이 좋아서 맞췄다 -> 시간이 오래 걸림(1500ms정도)
 */

public class BOJ_10942_팰린드롬 {
	static int N;
	static int[] arr;
	static int[][] isPalindrome;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		isPalindrome = new int[N+1][N+1];
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
		
			if (checkPalindrome(s, e)) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean checkPalindrome(int s, int e) {
		int start = s;
		int end = e;
		
		if (isPalindrome[start][end] == 1) return true;
		else if (isPalindrome[start][end] == -1) return false;
		
		boolean result = true;
		while (start <= end) {
			if (arr[start] != arr[end]) {
				result = false;
				break;
			}
			
			start++;
			end--;
		}
		
		int v = 0;
		if (result) v = 1;
		else v = -1;
		
		while (start <= s && end <= e) {
			if (isPalindrome[start][end] == v) break;
			isPalindrome[start--][end++] = v;
		}
		
		return result;
	}
}
