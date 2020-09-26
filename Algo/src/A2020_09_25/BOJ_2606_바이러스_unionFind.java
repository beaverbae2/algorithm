package A2020_09_25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2606_바이러스_unionFind {
	static int[] parent;
	static int N,M,answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			unionParent(a, b);
		}
		for (int v = 2; v < parent.length; v++) {
			if(findParent(1, v)) answer++;
		}
		System.out.println(answer);
	
	}
	
	private static int getParent(int v) {
		if(parent[v]==v) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if(a<b) parent[b] = a;
		else parent[a] = b;
	}
	
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if(a==b) return true;
		else return false;
	}
	
}
