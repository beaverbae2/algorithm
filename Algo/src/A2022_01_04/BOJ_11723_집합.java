package A2022_01_04;

import java.util.*;
import java.io.*;

/**
 * Bit masking
 * 30 MIN
 * 
 * @author beaverbae
 *
 */

public class BOJ_11723_집합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		StringBuilder sb;

		N = 0;
		M = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		int[] arr = new int[21];
		for (int i = 1; i <= 20; i++) {
			arr[i] = (1 << i);
		}

		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			int x;
			
			switch (op) {
			case "add":
				x = Integer.parseInt(st.nextToken());
				N |= arr[x];
				break;
			case "remove":
				x = Integer.parseInt(st.nextToken());
				if ((N & arr[x]) > 0) N ^= arr[x];
				break;
			case "check":
				x = Integer.parseInt(st.nextToken());
				if ((N & arr[x]) > 0) sb.append(1);
				else sb.append(0);
				sb.append("\n");
				break;
			case "toggle":
				x = Integer.parseInt(st.nextToken());
				N ^= arr[x];
				break;
			case "all":
				for (int i = 1; i <= 20; i++) {
					N |= arr[i];
				}
				break;
			case "empty":
				N = 0;
				break;

			default:
				break;
			}
		}

		System.out.println(sb);
	}
}
