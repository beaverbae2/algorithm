package A2021_08_07;

import java.util.*;
import java.io.*;

/**
 * Floyd Warshall
 * 어려웠던 부분
 * - 플로이드 와샬 초기화 : 시작점과 도착점이 동일한 경우 간선 크기는 0
 * - 경로 출력을 위한 배열 next[i][j] : i -> j 로 최소 간선 크기로 이동할때 j를 방문하기 직전에 거치는 노드
 * 
 * @author beaverbae
 * @see https://gre-eny.tistory.com/102
 */

public class BOJ_11780_플로이드_2_solution {
	static int N, M;
	static int[][] dist;
	static int[][] next;
	static final int INF = 987654321;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1][N+1];
		next = new int[N+1][N+1];
		sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;// 항상있어야함
				
				dist[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			dist[a][b] = Math.min(dist[a][b], w);
			next[a][b] = a;
		}
		
		floyd_warshall();
		print();
	}	

	private static void floyd_warshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						next[i][j] = next[k][j];
					}
				}
			}
		}
	}
	
	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (dist[i][j] == INF) {
					sb.append(0).append(" ");
				} else {
					sb.append(dist[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (dist[i][j] == INF || dist[i][j] == 0) {
					sb.append("0");
				} else {
					Stack<Integer> stack = new Stack<>();
					int pre = next[i][j];
					stack.push(j);
					while (pre != i) {
						stack.push(pre);
						pre = next[i][pre];
					}
					stack.push(i);
					
					sb.append(stack.size()).append(" ");
					while (!stack.isEmpty()) {
						sb.append(stack.pop()).append(" ");
					}
				}
				
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
