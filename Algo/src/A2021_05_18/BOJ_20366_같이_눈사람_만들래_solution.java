package A2021_05_18;

import java.util.*;
import java.io.*;

/**
 * Two Pointers
 * @author beaverbae
 * @see https://velog.io/@pss407/%EB%B0%B1%EC%A4%8020366-%EA%B0%99%EC%9D%B4-%EB%88%88%EC%82%AC%EB%9E%8C-%EB%A7%8C%EB%93%A4%EB%9E%98
 *
 */

public class BOJ_20366_같이_눈사람_만들래_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int answer = Integer.MAX_VALUE;
		
		for (int start1 = 0; start1 < N-3; start1++) {
			for (int end1 = 3; end1 < N; end1++) {
				int start2 = start1+1;
				int end2 = end1-1;
				
				while (start2 < end2) {
					int sub =  arr[start2] + arr[end2] - arr[start1] - arr[end1];
				
					if (answer > Math.abs(sub)) {
						answer = Math.abs(sub);
					}
					
					if (sub > 0) {
						end2--;
					} else {
						start2++;
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
}
