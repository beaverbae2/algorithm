package A2021_04_23;

import java.util.*;
import java.io.*;

/**
 * Backtracking
 * 36MIN
 * 오래걸린 이유
 * - 문자열을 파싱해서 계산하는 것
 * @author beaverbae
 *
 */

public class BOJ_7490_0_만들기 {
	static int N;
	static StringBuilder sb;
	static char[] arr = {' ', '+', '-'};// ASCII 순
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			makeExpression(1, "");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void makeExpression(int cnt, String s) {
		if (cnt == N) {
			s = s+N;
			if (isZero(s)) {
				sb.append(s).append("\n");
			}
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			makeExpression(cnt+1, s+cnt+arr[i]);
		}
		
	}
	
	private static boolean isZero(String s) {
		String exp = s.replaceAll(" ", "");
		int sum = 0;
		String left = "";
		char op = '0';
		
		int idx = exp.length();
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if (isOp(ch)) {
				idx = i+1;
				op = ch;
				break;
			} else {
				left += ch;
			}
		}
		
		sum = Integer.parseInt(left);
		
		String right = "";
		for (int i = idx; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if (isOp(ch)) {
				// 계산
				sum = calc(sum, Integer.parseInt(right), op);
				op = ch;
				right = "";
			} else {
				right += ch;
				if (i == exp.length() - 1) {
					sum = calc(sum, Integer.parseInt(right), op);
				}
			}
		}
		
		return sum == 0;
	}
	
	private static int calc(int a, int b, char op) {
		if (op == '+') {
			return a+b;
		} else {
			return a-b;
		}
	}
	
	private static boolean isOp(char ch) {
		if (ch == '+' || ch == '-') return true;
		return false;
	}
	
}
