package A2021_11_03;

import java.util.*;
import java.io.*;

/**
 * Set
 * 19MIN
 * @author beaverbae
 *
 */

public class BOJ_1484_다이어트 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean isExist = false;
		HashSet<Long> set = new HashSet<>(); 
		StringBuilder sb = new StringBuilder();
		long G = Integer.parseInt(br.readLine());
		
		for (int n = 1; n <= 100000; n++) {
			long num = (long) n * (long) n;
			if (set.contains(num - G)) {
				sb.append(n).append("\n");
				isExist = true;
			}
			set.add(num);
		}
		
		if (isExist) System.out.println(sb);
		else System.out.println(-1);
	}
}
