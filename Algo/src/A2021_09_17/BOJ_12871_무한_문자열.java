package A2021_09_17;

import java.util.*;
import java.io.*;

/**
 * String
 * 16MIN
 * @author beaverbae
 * 반복되는 최소 크기의 패턴이 서로 같은지 검사
 */

public class BOJ_12871_무한_문자열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		
		String str1 = "";
		String str2 = "";
		
		// 두 개의 문자열에서 반복되는 가장 작은 패턴 찾기
		for (int i = 0; i < s.length(); i++) {
			str1 += s.charAt(i);
			String[] arr = s.split(str1);
			if (arr.length == 0) break;
		}

		for (int i = 0; i < t.length(); i++) {
			str2 += t.charAt(i);
			String[] arr = t.split(str2);
			if (arr.length == 0) break;
		}
		
		if (str1.equals(str2)) System.out.println(1);
		else System.out.println(0);
	}
}
