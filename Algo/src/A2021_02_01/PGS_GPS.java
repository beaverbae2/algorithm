package A2021_02_01;

import java.util.*;
/**
 * 
 * @author beaverbae
 * @see https://tosuccess.tistory.com/144
 * @see https://wwiiiii.tistory.com/entry/%EC%B9%B4%EC%B9%B4%EC%98%A4-Code-Festival-%EB%B3%B8%EC%84%A0-16%EB%B2%88-%ED%92%80%EC%9D%B4
 * 
 */
public class PGS_GPS {
	public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
		List<Integer>[] graph = new List[n+1];
		
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < edge_list.length; i++) {
			int a = edge_list[i][0];
			int b = edge_list[i][1];
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int[][] dp = new int[k][n+1];
		final int INF = 987654321;
		
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = INF;
			}
		}
		
		dp[0][gps_log[0]] = 0;// 시작점
		
		for (int i = 1; i < gps_log.length; i++) {
			for (int j = 1; j < graph.length; j++) {
				// 제자리
				dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
			
				// 이동
				for (int v : graph[j]) {
					dp[i][j] = Math.min(dp[i][j], dp[i-1][v]);
				}
				
				// 경로 위치가 다른 경우
				if (gps_log[i] != j) {
					dp[i][j]++;
				}
			}
		}
		
		int answer = 0;
		if (dp[k-1][gps_log[k-1]] >= INF) {
			answer = -1;
		}else answer = dp[k-1][gps_log[k-1]];
		
		System.out.println(answer);
		return answer;
	}

	public static void main(String[] args) {
		new PGS_GPS().solution(7 , 10 , new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[] {1, 2, 3, 3, 6, 7});
		new PGS_GPS().solution(7 , 10 , new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[] {1, 2, 4, 6, 5, 7});
	}
}
