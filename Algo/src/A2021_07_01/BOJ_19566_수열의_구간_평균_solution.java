package A2021_07_01;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * 
 * @see https://velog.io/@rasauq1122/2020.08.14-%EC%98%A4%EB%8A%98%EC%9D%98-%EB%B0%B1%EC%A4%80
 * @author beaverbae
 * 
 * 어려웠던 부분
 * - 단순 투포인터로 접근 -> 오류 존재(단순 합이 아닌 평균이기 떄문) -> 구간합까진 생각
 * - 구간합에서 평균을 어떻게 나타낼 수 있을지 몰라 솔루션 참고
 *     - 구간합이 같은 두 인덱스 사이의 부분합은 0이 됨을 이용
 *     - 배열의 모든 값에 구하고자 하는 평균값 만큼 빼준 후 구간합 구함
 *     - 현재 인덱스와 뒤의 인덱스 중 구간합이 같은 인덱스의 개수 구함
 * 
 */

public class BOJ_19566_수열의_구간_평균_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		long[] prefix_sum;
		HashMap<Long, Long> map = new HashMap<>();
		long ans = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		prefix_sum = new long[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			prefix_sum[i] = Long.parseLong(st.nextToken()) - K;
		}
		
		map.put(0L, 1L); // 0번째 인덱스
		for (int i = 1; i <= N; i++) {
			prefix_sum[i] += prefix_sum[i-1];
			
			if (!map.containsKey(prefix_sum[i])) {
				map.put(prefix_sum[i], 1L);
			} else {
				map.put(prefix_sum[i], map.get(prefix_sum[i])+1L);
			}
		}
		
		for (int i = 0; i < N; i++) {
			long key = prefix_sum[i];
			
			if (map.containsKey(key)) {
				long value = map.get(key) - 1L;
				map.put(key, value);
				ans += value;
			}
		}
		
		System.out.println(ans);
	}
}
