package A2021_08_13;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 7MIN
 * @author beaverbae
 *
 */

public class BOJ_1789_수들의_합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());
		long n = 1L;
		
		int ans = 0;
		while (n <= S) {
			S -= n;
			n++;
			ans++;
		}
		System.out.println(ans);
	}
}
