package A2022_02_04;

import java.util.*;
import java.io.*;

/**
 * String
 * 9MIN
 * @author beaverbae
 *
 */

public class BOJ_6550_부분_문자열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		StringBuilder sb = new StringBuilder();
		
		while ((input = br.readLine()) != null) {
			String[] arr = input.split(" ");
			char[] A = arr[0].toCharArray(); 
			char[] B = arr[1].toCharArray(); 
			int i = 0, j = 0;
			
			while (true) {
				if (i == A.length) {
					sb.append("Yes").append("\n");
					break;
				}
				
				if (j == B.length) {
					sb.append("No").append("\n");
					break;
				}
				
				if (A[i] == B[j]) i++;
				
				j++;
			}
		}
		
		System.out.println(sb);
	}
}
