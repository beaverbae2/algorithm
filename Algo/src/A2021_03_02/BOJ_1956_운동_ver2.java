package A2021_03_02;

import java.util.*;
import java.io.*;

/**
 * Floyd-Warshall
 * @author beaverbae
 *
 */

public class BOJ_1956_운동_ver2 {
	static int[][] dist;
	static int V, E;
	static final int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		dist = new int[V + 1][V + 1];
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j)
					continue;

				dist[i][j] = INF;
			}
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			dist[a][b] = w;
		}

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		int answer = INF;
		for (int v = 1; v <= V; v++) {
			for (int next_v = 1; next_v <= V; next_v++) {
				if (v == next_v)
					continue;

				answer = Math.min(answer, dist[v][next_v] + dist[next_v][v]);
			}
		}

		answer = (answer == INF) ? -1 : answer;
		System.out.println(answer);
	}
}
