package A2021_01_30;

import java.util.*;
import java.io.*;

/**
 * 구간 합
 * @author beaverbae
 * @see https://www.crocus.co.kr/854
 */

public class BOJ_10986_나머지_합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		long answer = 0;
		
		int[] prefix_sum = new int[N];// prefix_sum[i] : A1 + A2 + ... + Ai
		int[] rem = new int[M];// rem[i] : 나머지가 i인 누적합의 개수
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			sum += num;
			sum = sum % M;
			
			prefix_sum[i] = sum;
			rem[prefix_sum[i]]++;
		}
		
		answer += (long) rem[0];// 누적합에서 나머지가 0인 것의 개수(자기 자신이 답) 
		for (int i = 0; i < rem.length; i++) {
			answer += (long) rem[i] * (rem[i] - 1) / 2;
		}
		System.out.println(answer);
	}
}
