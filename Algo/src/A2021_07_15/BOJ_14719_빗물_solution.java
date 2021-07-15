package A2021_07_15;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 
 * 완전히 꼬였었네
 * @author beaverbae
 * @see https://youngest-programming.tistory.com/415
 * 
 */

public class BOJ_14719_빗물_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int H, W;
		int[] arr;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		arr = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		for (int i = 1; i < W; i++) {
			int h = arr[i];
			int leftMax = h;
			int rightMax = h;
			
			for (int j = i - 1; j >= 0; j--) {
				leftMax = Math.max(leftMax, arr[j]);
			}
			
			for (int j = i+1; j < W; j++) {
				rightMax = Math.max(rightMax, arr[j]);
			}
			
			int max = Math.min(leftMax, rightMax);
			
			if (max > h) {
				ans += max - h;
			}
		}
		
		System.out.println(ans);
	}
}
