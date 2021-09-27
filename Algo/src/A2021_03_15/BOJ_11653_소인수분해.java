package A2021_03_15;

import java.util.*;
import java.io.*;

public class BOJ_11653_소인수분해 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = 2;
		StringBuilder sb = new StringBuilder();
		
		while (n > 1) {
			if (n % k == 0) {
				n /= k;
				sb.append(k).append("\n");
				k = 2;
			} else {
				k++;
			}
		}
		
		System.out.println(sb.toString());
	}
}
