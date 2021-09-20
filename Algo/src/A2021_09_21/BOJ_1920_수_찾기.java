package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * Set
 * 6MIN
 * @author beaverbae
 * 메모리를 최대한 아끼고자 했음
 * 이분탐색도 가능함
 * 
 */

public class BOJ_1920_수_찾기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<Integer> set = new HashSet<>();
		int N, M;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (N-- > 0) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while (M-- > 0) {
			if (set.contains(Integer.parseInt(st.nextToken()))) sb.append(1);
			else sb.append(0);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
