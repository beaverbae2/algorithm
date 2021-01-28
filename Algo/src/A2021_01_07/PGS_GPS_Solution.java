package A2021_01_07;

import java.util.*;
/**
 * 
 * @author beaverbae
 * @see https://tosuccess.tistory.com/144
 * @see https://wwiiiii.tistory.com/entry/%EC%B9%B4%EC%B9%B4%EC%98%A4-Code-Festival-%EB%B3%B8%EC%84%A0-16%EB%B2%88-%ED%92%80%EC%9D%B4
 * 
 */
public class PGS_GPS_Solution {
	public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
		int answer = 0;
		final int INF = 987654321;
		int[][] dp = new int[k][n+1]; //dp[i][j] : 경로 i번쨰가 j번 노드가 되면서, i번째 까지 경로를 유효하게 고쳐야 하는 최소 변화 개수
		List<Integer>[] graph = new List[n+1];
		
		//그래프 초기화
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		//그래프의 요소 추가
		for (int i = 0; i < edge_list.length; i++) {
			int a = edge_list[i][0];
			int b = edge_list[i][1];
			
			//양방향 그래프
			graph[a].add(b);
			graph[b].add(a);
		}
		
		//dp배열 초기화
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = INF;
			}
		}
		
		//dp 초기값 설정
		dp[0][gps_log[0]] = 0;
		
		
		for (int i = 1; i < gps_log.length; i++) {
			
			for (int j = 1; j < graph.length; j++) {
				dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);//가만히 있는 경우
				
				for (int v : graph[j]) {//v : 노드 j와 연결된 노드 
					dp[i][j] = Math.min(dp[i-1][v], dp[i][j]);
				}
				
				if (gps_log[i] != j) {//택시의 현위치가 gps_log 위치와 다른 경우
					dp[i][j] += 1;
				}
			}
		}
		
		if(dp[k-1][gps_log[k-1]] >= INF) answer = -1;
		else answer = dp[k-1][gps_log[k-1]];
		
		return answer;
	}
	

	public static void main(String[] args) {
		System.out.println(new PGS_GPS_Solution().solution(7, 10, new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}} , 6, new int[] {1, 2, 3, 3, 6, 7}));
		System.out.println(new PGS_GPS_Solution().solution(7, 10, new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}} , 6, new int[] {1, 2, 4, 6, 5, 7}));
	}
}
