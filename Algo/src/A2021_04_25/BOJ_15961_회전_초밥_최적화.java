package A2021_04_25;

import java.util.*;
import java.io.*;

/**
 * Map -> 배열, 배열로 구현 가능한 경우는 배열 쓰기 
 * @author beaverbae
 *
 */

public class BOJ_15961_회전_초밥_최적화 {
	static int[] arr, remain;
	static int N, D, K, C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		remain = new int[D+1];
		remain[C]++;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int temp = 1;
		for (int i = 0; i < K; i++) {
			int idx = arr[i];
			
			if (remain[idx] == 0) {
				temp++;
			}
			remain[idx]++;
		}
		
		int start = 0;
		int end = K;
		int answer = temp;
		
		for (int i = 0; i < N; i++) {
			// 맨 앞 요소 제외
			int first = arr[start++];
			if (remain[first] == 1) {
				temp--;
			}
			remain[first]--;
			
			// 맨 뒤 요소 추가
			int last = arr[end++];
			if (remain[last] == 0) {
				temp++;
			}
			remain[last]++;
			
			if (end == N) {
				end = 0;
			}
			if (start == N) {
				start = 0;
			}
			
			answer = Math.max(answer, temp);
		}
		System.out.println(answer);
	}
}
