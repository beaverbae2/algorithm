package A2021_03_11;

import java.util.*;
import java.io.*;

/**
 * Floyd-Warshall
 * 50MIN
 * 오래 걸린 이유 : 양방향, 타입
 * @author beaverbae
 *
 */

public class BOJ_13168_내일로_여행 {
	static double[][] graph1;// 일반 그래프
	static double[][] graph2;// 내일로 그래프
	static HashMap<String,Integer> cities;// 도시 이름 -> 번호
	static HashMap<String,Double> discounts;// 운행 수단 -> 할인율
	static int[] path;
	
	static int N, R, M, K;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	
		// 그래프 초기화
		graph1 = new double[N][N];
		graph2 = new double[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
			
				graph1[i][j] = INF*1.0;
				graph2[i][j] = INF*1.0;
			}
		}
		
		// 모든 도시, 도시 이름 -> 번호
		cities = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			String city = st.nextToken();
			cities.put(city, i);
		}
		
		// 방문할 도시
		M = Integer.parseInt(br.readLine());
		path = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int city_idx = cities.get(st.nextToken());
			path[i] = city_idx;
		}
		
		// 운송 수단에 따른 할인율
		discounts= new HashMap<>();
		discounts.put("Subway", 1.0);
		discounts.put("Bus", 1.0);
		discounts.put("Taxi", 1.0);
		discounts.put("Airplane", 1.0);
		discounts.put("KTX", 1.0);
		discounts.put("S-Train", 0.5);
		discounts.put("V-Train", 0.5);
		discounts.put("ITX-Saemaeul", 0.0);
		discounts.put("ITX-Cheongchun", 0.0);
		discounts.put("Mugunghwa", 0.0);
		
		// 그래프 생성
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			String trans = st.nextToken();
			int start = cities.get(st.nextToken());
			int end = cities.get(st.nextToken());
			double cost1 = Double.parseDouble(st.nextToken());
			
			double discount = discounts.get(trans);
			double cost2 = cost1 * discount; 
		
			graph1[start][end] = Math.min(graph1[start][end], cost1);
			graph2[start][end] = Math.min(graph2[start][end], cost2);
			
			graph1[end][start] = Math.min(graph1[end][start], cost1);
			graph2[end][start] = Math.min(graph2[end][start], cost2);
		}
		
		// 플로이드 와샬
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					graph1[i][j] = Math.min(graph1[i][j], graph1[i][k]+graph1[k][j]);
					graph2[i][j] = Math.min(graph2[i][j], graph2[i][k]+graph2[k][j]);
				}
			}
		}
		
		double final_cost1 = 0;
		double final_cost2 = 2*R;
		
		for (int i = 1; i < path.length; i++) {
			int start = path[i-1];
			int end = path[i];
			
			final_cost1 += 2 * graph1[start][end];
			final_cost2 += 2 * graph2[start][end];
		}
		
		if (final_cost2 < final_cost1) System.out.println("Yes");
		else System.out.println("No");
	}
	
	
}
