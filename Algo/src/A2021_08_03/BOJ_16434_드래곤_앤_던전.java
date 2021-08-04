package A2021_08_03;

import java.util.*;
import java.io.*;

/**
 * Binary search
 * 문제를 똑바로 읽자
 * @author beaverbae
 *
 */

public class BOJ_16434_드래곤_앤_던전 {
	static int N;
	static long init_hATK;
	static long[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		init_hATK = Integer.parseInt(st.nextToken());
	
		arr = new long[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Long.parseLong(st.nextToken());
			}
		}
		
		System.out.println(binary_search());
	}
	
	private static long binary_search() {
		long result = -1;
		long left = 1;
		long right = Long.MAX_VALUE-1;
	
		while (left <= right) {
			long mid = (left + right) / 2;
			
			if (isAlive(mid)) {
				right = mid - 1;
				result = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return result;
	}
	
	private static boolean isAlive(long hMaxHP) {
		long hCurHP = hMaxHP;
		long hATK = init_hATK;
		
		for (int i = 0; i < arr.length; i++) {
			long n = arr[i][0];
			long a = arr[i][1];
			long h = arr[i][2];
		
			if (n == 1) {
				long cnt = h / hATK;
				if (h % hATK == 0) cnt--;
				
				hCurHP -= cnt * a;
			} else {
				hATK += a;
				hCurHP += h;
				
				if (hCurHP > hMaxHP) hCurHP = hMaxHP;
			}
			
			if (hCurHP <= 0) return false;
		}
		
		return true;
	}
}
