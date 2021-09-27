package A2021_09_27;

import java.util.*;
import java.io.*;
/**
 * Two pointer
 * 24MIN
 * @author beaverbae
 * 종료조건이 놓친게 있었다. 테케를 시뮬레이팅해야함
 */
public class BOJ_1806_부분합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, S;
		int[] arr;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0, end = 0, sum = 0, len = N + 1;
		
		while (true) {
			if (sum >= S) {
				len = Math.min(len, end - start);
				sum -= arr[start++];
			} else {
				if (end == N) break;
				sum += arr[end++];
			}
		}
		
		if (len == N + 1) System.out.println(0);
		else System.out.println(len);
	}
}
