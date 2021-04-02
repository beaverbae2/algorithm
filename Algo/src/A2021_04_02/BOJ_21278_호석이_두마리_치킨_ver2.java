package A2021_04_02;

import java.util.*;
import java.io.*;

/**
 * Floyd-Warshall
 * N(정점 수)가 100 이하 이고, 모든 정점 중 2개 뽑아서 다른 정점들과 최단거리를 확인해야 하므로 플로이드 와샬 쓰는게 좋음
 * @author beaverbae
 *
 */

public class BOJ_21278_호석이_두마리_치킨_ver2 {
	static int[][] dist;
	static int N, M;
	static final int INF = 987654321;
	static int[] selected;// 조합
	static List<Pair> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				dist[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			dist[a][b] = 1;
			dist[b][a] = 1;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dist[i][j] += dist[i][j]; // 왕복 처리
			}
		}
		
		list = new ArrayList<>();
		selected = new int[2];
		
		comb(1, 0);// 2개 뽑기(조합)
		
		Collections.sort(list, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.w != o2.w) {
					return o1.w - o2.w;
				} else if (o1.v1 != o2.v1) {
					return o1.v1 - o2.v1;
				}
				return o1.v2 - o2.v2;
			}
		});
		Pair first = list.get(0);
		System.out.println(first.v1+" "+first.v2+" "+first.w);
	}
	
	private static void comb(int start, int idx) {
		if (idx == 2) {
			int total_w = 0;// 왕복 시간 합
			for (int i = 1; i <= N; i++) {
				total_w += Math.min(dist[selected[0]][i], dist[selected[1]][i]); // 둘 중 최소
			}
			
			list.add(new Pair(selected[0], selected[1], total_w));
			return;
		}
		
		for (int i = start; i <= N; i++) {
			selected[idx] = i;
			comb(i+1, idx+1);
		}
	}
	
	static class Pair {
		int v1, v2, w;

		public Pair(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Pair [v1=" + v1 + ", v2=" + v2 + ", w=" + w + "]";
		}
	}
}
