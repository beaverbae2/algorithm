package A2021_01_22;

import java.util.*;
import java.io.*;

/**
 * Two Pointers
 * 
 * @author beaverbae
 *
 */

public class BOJ_2003_수들의_합2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// Two Pointers

		// 초기화
		int start = 0, end = 0, sum = 0, answer = 0;

		// 탐색
		while (true) {
			if (sum >= M) {// start 이동 조건
				if (sum == M) {// 정답인 경우
					answer++;
				}
				sum -= arr[start++];

			} else {// end 이동 조건
				if (end == arr.length) {// 반복문 종료 조건
					break;
				}
				sum += arr[end++];
			}
		}

		System.out.println(answer);
	}
}
