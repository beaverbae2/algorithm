package A2022_02_07;

import java.util.*;
import java.io.*;

public class BOJ_1543_문서_검색 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		String A = br.readLine();
		String B = br.readLine();
		
		char[] arr = A.replaceAll(B, "1").toCharArray();
		for (char c : arr) {
			if (c == '1') ans++;
		}
		
		System.out.println(ans);
	}
}
