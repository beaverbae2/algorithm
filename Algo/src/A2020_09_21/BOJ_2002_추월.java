package A2020_09_21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_2002_추월 {
	static int N;
	static Map<String,Integer> map = new HashMap<>();
	static int[] enterNum;//진입차수
	static boolean[][] graph;
	static String[] endCars;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		enterNum = new int[N];
		graph = new boolean[N][N];
		endCars = new String[N];
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			map.put(br.readLine(), i);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				graph[i][j] = true;
				enterNum[j]++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			int car = map.get(br.readLine());
			if(enterNum[car]!=0) answer++;
			for (int next_car = 0; next_car < N; next_car++) {
				if(graph[car][next_car]) {
					enterNum[next_car]--;
				}
			}
		}
		System.out.println(answer);
	}
}
