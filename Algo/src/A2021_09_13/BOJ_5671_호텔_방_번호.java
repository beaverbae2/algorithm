package A2021_09_13;

import java.util.*;
import java.io.*;

/**
 * String
 * 14MIN
 * @author beaverbae
 * 출력할때 한 줄 띄우기 안함..
 */

public class BOJ_5671_호텔_방_번호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input;
		
		while ((input = br.readLine()) != null) {
			int ans = 0;
			HashSet<Character> set;
			int start, end;
			StringTokenizer st = new StringTokenizer(input);
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			for (int n = start; n <= end; n++) {
				char[] arr = (n+"").toCharArray();
				boolean isOk = true;
				set = new HashSet<>();
			
				for (char ch : arr) {
					if (set.contains(ch)) {  
						isOk = false;
						break;
					} else set.add(ch);
				}
				
				if (isOk) ans++;
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}
