package A2021_01_13;

import java.util.*;
import java.io.*;

/**
 * 68MIN
 * Two Pointers
 * @author beaverbae
 *
 */

public class BOJ_1806_부분합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = arr[0];
		int answer = 987654321;
		
		while(true) {
			if (sum >= S) {
				answer = Math.min(answer, end-start+1);
				
				sum -= arr[start];
				start++;
				if (start > end) {
					end = start;
					
					if (end >= N) break;
					
					sum = arr[end];
				}
			}else {
				end++;
				if (end >= N) break;
				
				sum += arr[end];
			}
		}
		
		if (answer == 987654321) System.out.println(0);
		else System.out.println(answer);
	}
}
