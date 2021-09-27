package A2021_06_21;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * 52MIN
 * 어려웠던 부분
 * - 누적합임을 떠올리기 쉽지 않았음
 * - 최솟값 개수 셀 때 마지막 인덱스 배제 -> 최솟값이 0인 경우 걸림
 * 
 * @author beaverbae
 *
 */

public class BOJ_3020_개똥벌레 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
	
		int[] arr = new int[H+1];
		for (int i = 1; i <= N; i++) {
			int k = Integer.parseInt(br.readLine());
			
			if (i % 2 == 1) {// 석순
				arr[0] += 1;
				arr[k] += -1;
			} else {// 종유석
				arr[H] += -1;
				arr[H-k] += 1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int min = Integer.MAX_VALUE;
		
		for (int i = 1; i < arr.length; i++) {
			arr[i] = arr[i-1] + arr[i];
			
			if (i == arr.length - 1) continue;
			
			min = Math.min(min, arr[i]);
		}
		
		sb.append(min).append(" ");
		
		int min_cnt = 0;
		for (int i = 0; i < arr.length-1; i++) {
			if (arr[i] == min) {
				min_cnt++;
			}
		}
		
		sb.append(min_cnt);
		System.out.println(sb.toString());
	}
}
