package A2021_04_01;

import java.util.*;
import java.io.*;

/**
 * Graph
 * 13MIN
 * @author beaverbae
 *
 */

public class BOJ_21316_스피카 {
	static int N = 12;
	static List<Integer>[] graph;
	static HashSet<Integer> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
	
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int answer = 0;
		for (int v = 1; v <= N; v++) {
			if (graph[v].size() != 3) continue;
			
			set = new HashSet<>();
			for (int nv : graph[v]) {
				set.add(graph[nv].size());
			}
			
			if (set.size() == 3) {
				answer = v;
				break;
			}
		}
		System.out.println(answer);
	}
}
