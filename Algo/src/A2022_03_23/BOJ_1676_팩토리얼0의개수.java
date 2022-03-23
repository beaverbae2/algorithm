package A2022_03_23;

import java.util.*;
import java.io.*;

/**
 * Math
 * 11MIN
 * @author beaverbae
 *
 */

public class BOJ_1676_팩토리얼0의개수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int two = 0, five = 0;
		int N = Integer.parseInt(br.readLine());
		for (int i = N; i > 1; i--) {
			int n = i;
			while (n % 5 == 0) {
				n /= 5;
				five++;
			}
			while (n % 2 == 0) {
				n /= 2;
				two++;
			}
		}
		
		System.out.println(Math.min(two, five));
	}
}
