package A2021_04_01;

import java.util.*;
import java.io.*;
/**
 * Greedy
 * @author beaverbae
 *
 */

public class BOJ_21313_문어 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int n = N/2;
		
		for (int i = 0; i < n; i++) {
			sb.append("1 2 ");
		}
		
		if (N % 2 == 1) {
			sb.append("3");
		}
		System.out.println(sb.toString());
	}
}
