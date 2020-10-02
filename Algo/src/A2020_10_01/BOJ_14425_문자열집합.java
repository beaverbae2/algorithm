package A2020_10_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_14425_문자열집합 {
	static int N,M,answer;
	static String[] T;
	static HashMap<String,Boolean> S = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = new String[M];
		
		for (int i = 0; i < N; i++) {
			S.put(br.readLine(), true);
		}
		for (int i = 0; i < T.length; i++) {
			T[i] = br.readLine();
		}
		for (int i = 0; i < T.length; i++) {
			for (int j = 0; j < S.size(); j++) {
				if (S.get(T[i])==null) continue;
				else if(S.get(T[i])) {
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);
		
		
	}
}
