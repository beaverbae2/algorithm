package A2021_01_13;

import java.util.*;
import java.io.*;

/**
 * 15MIN
 * DFS - subset
 * @author beaverbae
 * 
 */

public class BOJ_14225_부분수열의_합 {
	static int N;
	static int[] arr;
	static boolean[] visited;
	static HashMap<Integer, Boolean> map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		visited = new boolean[N];
		map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0);
		
		int answer = 1;
		while(true) {
			if (map.get(answer) == null) break;
			answer++;
		}
		
		System.out.println(answer);
	}

	private static void subset(int r) {
		if (r == N) {
			map.put(getSum(), true);
			return;
		}
		
		visited[r] = true;
		subset(r+1);
		visited[r] = false;
		subset(r+1);
	}

	private static int getSum() {
		int result = 0;
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) continue;
			
			result += arr[i];
		}
		return result;
	}
	
	
	
}
