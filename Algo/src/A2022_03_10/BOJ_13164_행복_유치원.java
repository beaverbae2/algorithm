package A2022_03_10;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * @author beaverbae
 * @see https://velog.io/@jsin2475/BOJ-13164-%ED%96%89%EB%B3%B5-%EC%9C%A0%EC%B9%98%EC%9B%90
 * 
 * - 왜 이분탐색이 안되는지 생각해보기
 * - 그리디 접근 어렵
 */

public class BOJ_13164_행복_유치원 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
	
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			int diff = arr[i] - arr[i-1]; 
			list.add(diff);
			ans += diff;
		}
		
		Collections.sort(list, Collections.reverseOrder());
		for (int k = 0; k < K-1; k++) {
			ans -= list.get(k);
		}
		
		System.out.println(ans);
	}
}
