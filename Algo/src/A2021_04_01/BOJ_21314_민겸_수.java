package A2021_04_01;

import java.util.*;
import java.io.*;

/**
 * Math
 * @author beaverbae
 *
 */

public class BOJ_21314_민겸_수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		Queue<Integer> k_queue = new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 'K') {
				k_queue.offer((i+1));
			}
		}
		
		StringBuilder min = new StringBuilder();
		StringBuilder max = new StringBuilder();
		
		if (k_queue.isEmpty()) {
			min.append(1);
			max.append(1);
			for (int i = 1; i < arr.length; i++) {
				min.append(0);
				max.append(1);
			}
		} else {
			int top = 0;
			while (!k_queue.isEmpty()) {
				int next_top = k_queue.poll();
				int len = next_top - top - 1;
				max.append(5);
				if (len > 0) min.append(1);
				
				for (int i = 0; i < len; i++) {
					max.append(0);
				}
				
				for (int i = 0; i < len-1; i++) {
					min.append(0);
				}
				min.append(5);
				top = next_top;
			}
			
			// 남은 M에 대해 처리
			int len = arr.length - top;
			if (len > 0) {
				max.append(1);
				min.append(1);
				for (int i = 1; i < len; i++) {
					max.append(1);
					min.append(0);
				}
			}
		}
		
		System.out.println(max);
		System.out.println(min);
		
	}
}
