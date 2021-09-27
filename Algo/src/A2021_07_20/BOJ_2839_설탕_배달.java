package A2021_07_20;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 23MIN
 * - 남은 소금의 양이 5로 나누어 떨어질때까지, 3kg씩 빼기
 * @author beaverbae
 *
 */

public class BOJ_2839_설탕_배달 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		int N = Integer.parseInt(br.readLine());
		
		while (N % 5 != 0 && N >= 0) {
			N -= 3;
			ans++;
		}
		
		if (N < 0) {
			System.out.println(-1);
		} else {
			ans += N/5;
			System.out.println(ans);
		}
	}
}
