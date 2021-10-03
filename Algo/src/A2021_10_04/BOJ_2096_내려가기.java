package A2021_10_04;

import java.util.*;
import java.io.*;

/**
 * Sliding window
 * 13MIN
 * @author beaverbae
 *
 */

public class BOJ_2096_내려가기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] max = new int[3];
		int[] min = new int[3];
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n0 = Integer.parseInt(st.nextToken());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
		
			int[] next_max = new int[3];
			int[] next_min = new int[3];
			
			next_max[0] = Math.max(max[0] + n0, max[1] + n0);
			next_max[1] = Math.max(max[0] + n1, Math.max(max[1] + n1, max[2] + n1));
			next_max[2] = Math.max(max[1] + n2, max[2] + n2);
			
			next_min[0] = Math.min(min[0] + n0, min[1] + n0);
			next_min[1] = Math.min(min[0] + n1, Math.min(min[1] + n1, min[2] + n1));
			next_min[2] = Math.min(min[1] + n2, min[2] + n2);
		
			for (int i = 0; i < 3; i++) {
				max[i] = next_max[i];
				min[i] = next_min[i];
			}
		}
		
		System.out.println(Math.max(max[0], Math.max(max[1], max[2]))+" "+Math.min(min[0], Math.min(min[1], min[2])));
	}
}	
