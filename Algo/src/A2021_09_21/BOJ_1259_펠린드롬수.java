package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * 5MIN
 * String
 * @author beaverbae
 *
 */

public class BOJ_1259_펠린드롬수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while (true) {
			String str = br.readLine();
			if (str.equals("0")) break;
			int s = 0;
			int e = str.length() - 1;
			
			while (s <= e) {
				if (str.charAt(s) != str.charAt(e)) break; 
				s++;
				e--;
			}
			
			if (s > e) sb.append("yes");
			else sb.append("no");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
