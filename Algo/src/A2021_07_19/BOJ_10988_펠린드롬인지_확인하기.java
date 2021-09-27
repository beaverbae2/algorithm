package A2021_07_19;

import java.util.*;
import java.io.*;

/**
 * 문자열
 * 3MIN
 * @author beaverbae
 *
 */

public class BOJ_10988_펠린드롬인지_확인하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		
		System.out.println(isPelindrome(arr));
	}

	private static int isPelindrome(char[] arr) {
		int l = 0;
		int r = arr.length - 1;
		
		while (l <= r) {
			if (arr[l++] != arr[r--]) return 0;
		}
		
		return 1;
	}
}
