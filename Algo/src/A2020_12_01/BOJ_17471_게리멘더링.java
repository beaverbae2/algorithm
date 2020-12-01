package A2020_12_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_게리멘더링 {
	static int N;
	static int[] population;
	static boolean[][] graph;
	static boolean[] selected;
	static int answer, lastSelectedVertex,lastNotSelectedVertex;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		graph = new boolean[N+1][N+1];
		selected = new boolean[N+1];
		answer = Integer.MAX_VALUE;
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int a = 1; a <= N; a++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int i = 0; i < n; i++) {
				int b = Integer.parseInt(st.nextToken());
				graph[a][b] = true;
				graph[b][a] = true;
			}
		}

		subset(1,-1,-1,0);
		if(answer==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}


	private static void subset(int r,int lastSelected, int lastNotSelected, int selected_num) {
		if(r==N+1) {
			//게리멘더링
			answer = Math.min(answer, bfs(lastSelected, lastNotSelected, selected_num));
			return;
		}
		
		selected[r] = true;
		subset(r+1,r,lastNotSelected, selected_num+1);
		
		selected[r] = false;
		subset(r+1,lastSelected, r, selected_num);
	
	}


	private static int bfs(int lastSelectedVertex, int lastNotSelectedVertex,int selected_num) {
	
		if(lastNotSelectedVertex==-1||lastSelectedVertex==-1) return Integer.MAX_VALUE;
		//선거구 1
		int result = 0;
	
		int sum1 = 0;
		int search_num1 = 0;
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(lastSelectedVertex);
		visited[lastSelectedVertex] = true;
		sum1 += population[lastSelectedVertex];
		search_num1 += 1;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next_v = 1; next_v <= N; next_v++) {
				if(!graph[v][next_v]||!selected[next_v]) continue;
				
				if(!visited[next_v]) {
					q.offer(next_v);
					visited[next_v] = true;
					sum1 += population[next_v];
					search_num1++;
				}
			}
		}

		if(search_num1!=selected_num) return Integer.MAX_VALUE;
		
		//선거구2
		int sum2 = 0;
		int search_num2 = 0;
		q = new LinkedList<>();
		visited = new boolean[N+1];
		q.offer(lastNotSelectedVertex);
		visited[lastNotSelectedVertex] = true;
		sum2 += population[lastNotSelectedVertex];
		search_num2 += 1;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next_v = 1; next_v <= N; next_v++) {
				if(!graph[v][next_v]||selected[next_v]) continue;
				
				if(!visited[next_v]) {
					q.offer(next_v);
					visited[next_v] = true;
					sum2 += population[next_v];
					search_num2++;
				}
			}
		}

		if(search_num2 != (N-selected_num)) return Integer.MAX_VALUE;
		
		result = Math.abs(sum1-sum2);
		return result;
	}
}
