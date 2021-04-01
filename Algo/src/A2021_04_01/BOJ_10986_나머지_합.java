package A2021_04_01;

import java.util.*;
import java.io.*;

public class BOJ_10986_나머지_합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] prefix_sum = new int[N+1];
		int[] rem = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			prefix_sum[i] = (prefix_sum[i-1] + Integer.parseInt(st.nextToken())) % M;
			rem[prefix_sum[i]]++; 
		}
		
		// 나머지가 0인 경우는 자기 자신이 답이 될 수 있으므로
		long ans = 0;
		ans += (long) rem[0];
		
		// 구간 2개 선택 -> 조합(nC2)
		for (int i = 0; i < rem.length; i++) {
			ans += (long) rem[i] * (rem[i]-1) / 2;
		}
		
		System.out.println(ans);
	}
}
