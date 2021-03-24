package A2021_03_24;

import java.util.*;
import java.io.*;

/**
 * Tree, Graph
 * 39MIN
 * 자식 노드에서 루트 노드로 탐색시 길은 오직 하나
 * @author beaverbae
 * 
 */

public class BOJ_3584_가장_가까운_공통_조상 {
	static int N, root;
	static List<Integer>[] graph;
	static boolean[] isChild;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			isChild = new boolean[N+1];
			
			// 그래프 구성
			graph = new List[N+1];
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
			
				graph[b].add(a);
				isChild[b] = true;
			}
			
			// 루트 찾기
			for (int i = 1; i < isChild.length; i++) {
				if (!isChild[i]) {
					root = i;
					break;
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dest1 = Integer.parseInt(st.nextToken());
			int dest2 = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> path1 = getPath(dest1, root, new ArrayList<Integer>());
			ArrayList<Integer> path2 =getPath(dest2, root, new ArrayList<Integer>());
			
			int len = Math.min(path1.size(), path2.size());
			int answer = 0;
			
			// 가장 가까운 공통 조상 찾기
			for (int i = 0; i < len; i++) {
				int p1 = path1.get(i);
				int p2 = path2.get(i);
				
				if (p1 == p2) {
					answer = p1;
				} else break;
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static ArrayList<Integer> getPath(int start, int end, List<Integer> path) {
		int v = start;
		while(true) {
			path.add(v);
			if (v == end) break;
			v = graph[v].get(0);
		}
		
		ArrayList<Integer> real_path = new ArrayList<>();
		for (int i = path.size() - 1; i >= 0; i--) {
			real_path.add(path.get(i));
		}
		
		return real_path;
	}
}
