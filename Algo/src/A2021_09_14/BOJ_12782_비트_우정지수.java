package A2021_09_14;

import java.util.*;
import java.io.*;

/**
 * Math
 * 30MIN
 * @author beaverbae
 *
 */

public class BOJ_12782_비트_우정지수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] arr1 = st.nextToken().toCharArray();
			char[] arr2 = st.nextToken().toCharArray();
			
			Queue<Integer> q1 = new LinkedList<>();
			Queue<Integer> q2 = new LinkedList<>();
		
			for (int i = 0; i < arr1.length; i++) {
				if (arr1[i] == '1' && arr2[i] == '0') q1.offer(i);
				else if (arr1[i] == '0' && arr2[i] == '1') q2.offer(i);
			}
			
			sb.append((Math.min(q1.size(), q2.size()) + Math.abs(q1.size() - q2.size()))).append("\n");
		}
		System.out.println(sb);
	}
}
