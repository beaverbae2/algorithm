package A2020_09_21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//플로이드 와샬 알고리즘
//대문자로 출력해서 틀렸던거 실화냐....
public class BOJ_13168_내일로여행 {
	static int[][] nailro, general;
	static int N, M, K;
	static int nailro_sum, general_sum;
	static Map<String, Integer> city = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		nailro_sum = Integer.parseInt(st.nextToken());
		nailro = new int[N][N];
		general = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i!=j) {
					nailro[i][j] = 987654321;
					general[i][j] = 987654321;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			city.put(st.nextToken(), i);
		}
		M = Integer.parseInt(br.readLine());
		String[] travel = br.readLine().split(" ");
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			String[] strArr = br.readLine().split(" ");
			int a = city.get(strArr[1]);
			int b = city.get(strArr[2]);
			int cost = Integer.parseInt(strArr[3]);
			//nailro
			int nailro_cost = getNailroCost(strArr[0], cost);
			nailro[a][b] = Math.min(nailro[a][b],nailro_cost);
			nailro[b][a] = nailro[a][b];
			
			//general
			general[a][b] = Math.min(general[a][b], cost); 
			general[b][a] = general[a][b]; 
			
		}
		
		//플로이드 와샬
		for (int k = 0; k < N; k++) {//중간 노드
			for (int i = 0; i < N; i++) {//출발 노드
				for (int j = 0; j < N; j++) {//도착 노드
					if(nailro[i][k]+nailro[k][j]<nailro[i][j]) {	
						nailro[i][j] = nailro[i][k]+nailro[k][j];
						
					}
					if(general[i][k]+general[k][j]<general[i][j]) {	
						general[i][j] = general[i][k]+general[k][j];
					}
				}
			}
		}
		
		for (int i = 0; i < M-1; i++) {
			int a = city.get(travel[i]);
			int b = city.get(travel[i+1]);
			nailro_sum += nailro[a][b];
			general_sum += general[a][b];
		}
		String answer = (nailro_sum < general_sum) ? "Yes" : "No";
		System.out.println(answer);
		
	}

	private static int getNailroCost(String transport, int cost) {
		if(transport.equals("Mugunghwa")||transport.equals("ITX-Saemaeul")||transport.equals("ITX-Cheongchun")) {
			return 0;
		}else if(transport.equals("S-Train")||transport.equals("V-Train")) {
			return cost/2;
		}else {
			return cost;
		}
	}
}
