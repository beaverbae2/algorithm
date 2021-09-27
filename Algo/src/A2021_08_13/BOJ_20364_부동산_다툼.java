package A2021_08_13;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 46MIN
 * 처음 마주치는 점유한 땅을 찾을떄 루트부터 순차적으로 확인
 * @author beaverbae
 *
 */

public class BOJ_20364_부동산_다툼 {
	static boolean[] visited;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		visited = new boolean[N+1];
		for (int i = 0; i < Q; i++) {
			int duck = Integer.parseInt(br.readLine());
			
			int pos = getPosition(duck);
			sb.append(pos).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int getPosition(int n) {
		int cur = n;
		
		while (cur > 0) {
			if (visited[cur]) {
				return getFirstParent(cur);
			}
			
			cur = cur / 2;
		}
		
		visited[n] = true;
		return 0;
	}

	private static int getFirstParent(int n) {
		int cur = n;
		Stack<Integer> stack = new Stack<>();
		
		while (cur >= 1) {
			stack.push(cur);
			cur = cur / 2;
		}
		
		while (!stack.isEmpty()) {
			int top = stack.pop();
			if (visited[top]) return top;
		}
		
		return 0;
	}
}
