package A2021_08_04;

import java.util.*;
import java.io.*;

/**
 * 13MIN
 * String
 * 인덱싱을 꼼꼼하게 하자 -> 출력 반드시 확인
 * @author beaverbae
 *
 */

public class BOJ_1251_단어_나누기 {
	static int N;
	static char[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = br.readLine().toCharArray();
		N = arr.length;
		
		String ans = "";
		for (int i = 0; i < N; i++) {
			ans += 'z';
		}
		
		for (int i = 1; i <= N-2; i++) {
			String s1 = getString(0, i-1);
			
			for (int j = i+1; j <= N-1; j++) {
				String s2 = getString(i, j-1);
				String s3 = getString(j, N-1);
				
				String temp = s1 + s2 + s3;
				
				if (ans.compareTo(temp) > 0) {
					ans = temp;
				}
			}
		}
		
		System.out.println(ans);
	}

	private static String getString(int s, int e) {
		String str = "";
		for (int i = e; i >= s; i--) {
			str += arr[i];
		}
		
		return str;
	}
}
