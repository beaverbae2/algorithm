package A2021_04_23;

import java.util.*;
import java.io.*;

/**
 * Parametric Search
 * 오래 걸린 이유
 * - 수학 공식으로 풀려다가 나락감
 * - right의 범위를 어떻게 잡아야 하나 고민
 * @author beaverbae
 *
 */

public class BOJ_1072_게임 {
	static long X, Y, Z;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		Z = (100*Y)/X;
		
		long answer = parametric_search();
		if (answer == X+1) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	private static long parametric_search() {
		long result = X+1;
		
		long left = 0;
		long right = X;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			
			long z = (100*(Y+mid)) / (X+mid);
			
			if (z < Z+1) {
				left = mid + 1;
			} else {
				right = mid - 1;
				result = mid;
			}
		}
		
		
		return result;
	}
}
