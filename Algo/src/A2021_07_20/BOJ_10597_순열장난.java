package A2021_07_20;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 75MIN
 * - 어려웠던 부분
 *     - 한자리 수와 두자리 수를 뽑는 경우의 수 나누기
 *     - 순열을 입력 순서대로 출력해야하는 것을 놓침
 * @author beaverbae
 *
 */

public class BOJ_10597_순열장난 {
	static char[] input;
	static int[] arr;
	static int N;
	static boolean[] visited = new boolean[51];
	static ArrayList<Integer> list;
	static LinkedHashSet<Integer> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine().toCharArray();
		N = input.length;
		arr = new int[N];
		list = new ArrayList<>();
		set = new LinkedHashSet<Integer>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = input[i] - '0';
		}
		
		dfs(0);
	}

	private static void dfs(int i) {
		if (i == arr.length) {
			int max = 0;
			for (int n : set) {
				max = Math.max(max, n);
			}
			
			for (int n = 1; n <= max; n++) {
				if (n > 50 || !visited[n]) return;
			}
			
			StringBuilder sb = new StringBuilder();
			for (int n : set) {
				sb.append(n).append(" ");
			}
			System.out.println(sb.toString());
			System.exit(0);
		}
		
		
		int n;
		if (i < N-1 && arr[i] > 0) {
			n = arr[i]*10 + arr[i+1];
			
			if (n <= 50 && !visited[n]) {
				visited[n] = true;
				set.add(n);
				dfs(i+2);
				visited[n] = false;
				set.remove(n);
			}
		}
		
		n = arr[i];
		if (n != 0 && !visited[n]) {
			visited[n] = true;
			set.add(n);
			dfs(i+1);
			visited[n] = false;
			set.remove(n);
		}
	}
}
