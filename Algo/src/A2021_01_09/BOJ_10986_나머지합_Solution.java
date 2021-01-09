package A2021_01_09;

import java.util.*;
import java.io.*;

/**
 * 구간합
 * 
 * @author beaverbae
 * @see https://www.crocus.co.kr/854
 * 
 */

public class BOJ_10986_나머지합_Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] prefix_sum = new int[N];// prefix_sum[i] = (A1 + ... + Ai) % M
		int[] rem = new int[M];// rem[i] : 나머지가 i인 숫자의 개수
		int sum = 0;
		long answer = 0;
		st = new StringTokenizer(br.readLine());
		
		// 구간합 구하기
		for (int i = 0; i < N; i++) {
			sum += Integer.parseInt(st.nextToken());
			sum = sum % M;

			prefix_sum[i] = sum;
			
			rem[prefix_sum[i]]++;
		}
		
		answer += (long) rem[0];// rem[0] -> 자기 자신이 답이 될 수 있음
		for (int i = 0; i < rem.length; i++) {
			// 2개씩 뽑기 : nC2 = n(n-1)/2
			answer += (long) rem[i] * (rem[i] - 1) / 2;
		}
		System.out.println(answer);

	}
}
