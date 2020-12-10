package A2020_12_08;

import java.util.*;

public class PGS_줄서는방법_시간초과 {
	static int[] arr, selected;
	static int[] result;
	static boolean[] visited;
	static long cnt;
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(3, 5)));
	}
	
	public static int[] solution(int n, long k) {
		arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		selected = new int[n];
		
		result = new int[n];
		visited = new boolean[n];
		cnt = 0;
		
		long fact = getFactorial(n);
		dfs(0,n,k);
		return result;
	}
	
	public static long getFactorial(int n) {
		if(n==1) return 1;
		return n*getFactorial(n-1);
	}
	
	public static void dfs(int r, int n, long k) {
		if(r==n) {
			cnt++;
			if(cnt==k) {
				for (int i = 0; i < selected.length; i++) {
					result[i] = selected[i];
				}
			}
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			selected[r] = arr[i];
			dfs(r+1,n,k);
			visited[i] = false;
		}
	}


}
