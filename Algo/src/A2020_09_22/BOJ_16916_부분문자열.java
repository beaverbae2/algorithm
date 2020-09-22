package A2020_09_22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//kmp 알고리즘
public class BOJ_16916_부분문자열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String pattern = br.readLine();
		System.out.println(kmp(str, pattern));
	}
	
	private static int[] getPi(String pattern) {
		char[] p = pattern.toCharArray();
		int[] pi = new int[p.length];
		int j = 0;
		
		for (int i = 1; i < p.length; i++) {
			while(j>0 && p[i]!=p[j]) {
				j = pi[j-1];
			}
			if(p[i]==p[j]) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
	
	private static int kmp(String str,String pattern) {
		int[] pi = getPi(pattern);
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		int j = 0;
		int result = 0;
		
		for (int i = 0; i < s.length; i++) {
			while(j>0 && s[i]!=p[j]) {
				j = pi[j-1];
			}
			
			if(s[i]==p[j]) {
				if(j==p.length-1) {
					result = 1;
					return result;
					//j=pi[j];
				}else j++;
			}
		}
		return result;
	}
}
