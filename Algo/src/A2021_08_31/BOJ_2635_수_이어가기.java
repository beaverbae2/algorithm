package A2021_08_31;

import java.util.*;
import java.io.*;

/**
 * 27MIN
 * Brute force
 * 차근차근 구현하면 되는 문제
 * @author beaverbae
 *
 */

public class BOJ_2635_수_이어가기 {
	static LinkedList<Integer> list;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new LinkedList<>();
		for (int i = 0; i <= N; i++) {
			LinkedList<Integer> temp_list = new LinkedList<>();
			temp_list.add(N);
			temp_list.add(i);
			
			while (true) {
				int b = temp_list.removeLast();
				int a = temp_list.removeLast();
				int n = a - b;
				
				temp_list.add(a);
				temp_list.add(b);
				
				if (n < 0) break;
				
				temp_list.add(n);
			}
			
			if (temp_list.size() > list.size()) {
				list = temp_list;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		for (int n : list) {
			sb.append(n).append(" ");
		}
		
		System.out.println(sb);
	}
}
