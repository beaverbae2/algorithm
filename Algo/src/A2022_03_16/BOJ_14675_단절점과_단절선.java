package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 17MIN
 * @author beaverbae
 * - 단절점 : 연결된 정점 개수가 2개 이상
 * - 단절선 : 모든 간선
 * 
 */

public class BOJ_14675_단절점과_단절선 {
	static int N, Q;
	static List<Integer>[] tree;
	static boolean[] isCut;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isCut = new boolean[N+1];
		tree = new List[N+1];
		StringBuilder sb = new StringBuilder();
		
		for (int v = 1; v <= N; v++) {
			tree[v] = new ArrayList<>();
		}
		
		for (int i =0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		for (int v = 1; v <= N; v++) {
			if (tree[v].size() > 1) isCut[v] = true;
		}
		
		Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
		
			if (t == 1) {
				if (isCut[k]) sb.append("yes");
				else sb.append("no");
			} else {
				sb.append("yes");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
