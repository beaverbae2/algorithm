package A2021_11_24;

import java.util.*;
import java.io.*;

/**
 * 자료구조
 * 77MIN
 * @author beaverbae
 * 어려웠던 점
 * - 정규식 : 대괄호는 앞에 \\ 붙여야함
 * - 3항연산자 : 리턴결과를 받는 변수가 반드시 있어야함
 * - 로직 오류 : 비어있는 경우 누락, 에러 발생시 break 처리
 */

public class BOJ_5430_AC {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC, N;
		char[] P;
		LinkedList<Integer> list;
		StringBuilder sb;
		
		TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		while (TC-- > 0) {
			P = br.readLine().toCharArray();
			N = Integer.parseInt(br.readLine());
			String temp = br.readLine();
			list = new LinkedList<>();
			boolean isReverse = false;
			boolean isError = false;
			
			String[] tempArr = temp.split("\\[|,|\\]");
			
			for (int i = 1; i < tempArr.length; i++) {
				list.add(Integer.parseInt(tempArr[i]));
			}
			
			for (char p : P) {
				if (p == 'R') {
					isReverse = !isReverse;
				} else {
					if (list.isEmpty()) {
						sb.append("error").append("\n");
						isError = true;
						break;// 누락1
					} else {
						int removed = isReverse ? list.removeLast() : list.removeFirst();
					}
				}
			}
			
			if (isError) continue;
			
			sb.append("[");
			
			if (list.isEmpty()) sb.append("]");// 누락2
			
			while (!list.isEmpty()) {
				if (isReverse) {
					sb.append(list.removeLast());
				} else {
					sb.append(list.removeFirst());
				}
				
				if (list.isEmpty()) sb.append("]");
				else sb.append(",");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
