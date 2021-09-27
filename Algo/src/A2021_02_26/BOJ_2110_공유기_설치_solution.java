package A2021_02_26;

import java.util.*;
import java.io.*;

/**
 * Parametric Search
 * @author beaverbae
 *
 */

public class BOJ_2110_공유기_설치_solution {
	static int N, C;
	static int[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(input);
	
		System.out.println(parametric_search());
	}

	private static int parametric_search() {
		int result = 0;
		int start = 1;
		int end = input[N-1];
		
		while (start <= end) {
			int mid = (start+end)/2;
			
			int cnt = 1;// 설치할 수 있는 공유기 개수
			
			int first = input[0];
			for (int i = 0; i < input.length; i++) {
				if (input[i] - first >= mid) {
					cnt++;
					first = input[i];
				}
			}
			
			if (cnt >= C) {
				result = mid;
				start = mid+1;
			} else {
				end = mid-1;
			}
			
		}
		
		
		return result;
	}
}
