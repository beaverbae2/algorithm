package A2021_04_05;

import java.util.*;
import java.io.*;

/**
 * FAIL
 * Greedy : 무엇이 잘못됐을꼬....
 * @author beaverbae
 *
 */

public class BOJ_9177_단어_섞기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("Data set ").append(tc).append(": ");
			String str = br.readLine();
			String[] split = str.split(" ");
			
			char[] w1 = split[0].toCharArray();
		 	char[] w2 = split[1].toCharArray();
			char[] target = split[2].toCharArray();
			
			int len1 = w1.length;
			int len2 = w2.length;
			int t_len = target.length;
			
			
			
			if (t_len != len1 + len2) {
				sb.append("no").append("\n");
				continue;
			}
			
			int idx1 = 0;
			int idx2 = 0;
			int t_idx = 0;
			
			boolean flag = true;
			boolean w1_flag = true;
			boolean w2_flag = true;
			
			while (t_idx < t_len) {
				if (t_idx+1 < t_len && idx1+1 < len1 && idx2+1 < len2 && target[t_idx] == w1[idx1] && target[t_idx] == w2[idx2]) {
					if (target[t_idx+1] == w1[idx1+1]) {
						t_idx++;
						idx1++;
					} else if (target[t_idx+1] == w2[idx2+1]) {
						t_idx++;
						idx2++;
					} else {
						flag = false;
						break;
					}
				}else if (w1_flag && target[t_idx] == w1[idx1]) {
					t_idx++;
					idx1++;
					if (idx1 == len1) w1_flag = false;
				} else if (w2_flag && target[t_idx] == w2[idx2]) {
					t_idx++;
					idx2++;
					if (idx2 == len2) w2_flag = false;
				} else {
					flag = false;
					break;
				}
				
			}
			
			if (flag) {
				sb.append("yes").append("\n");
			} else {
				sb.append("no").append("\n");
			}
			
		}
		System.out.println(sb.toString());
	}
}
