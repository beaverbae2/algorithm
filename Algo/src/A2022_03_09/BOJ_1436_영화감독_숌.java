package A2022_03_09;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 10MIN
 * @author beaverbae
 *
 */

public class BOJ_1436_영화감독_숌 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 666;
		int ans = 0;
		int target = Integer.parseInt(br.readLine());
		
		while (true) {
			if (Integer.toString(N).contains("666")) ans++;
			if (ans == target) break;
			N++;
		}
		
		System.out.println(N);
	}
}
