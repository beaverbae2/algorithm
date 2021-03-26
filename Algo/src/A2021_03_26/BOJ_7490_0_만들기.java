package A2021_03_26;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 76MIN
 * 오래 걸린 이유 : 문제 똑바로 않 읽음, ' ' 해결 문제
 * @author beaverbae
 *
 */

public class BOJ_7490_0_만들기 {
	static LinkedList<String> list;
	static char[] ops;
	static int[] arr;
	static StringBuilder sb;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			list = new LinkedList<>();
			ops = new char[N-1];
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = i+1;
			}
			
			dfs(0);
			Collections.sort(list);
			for (String str : list) {
				sb.append(str).append("\n");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int idx) {
		if (idx == N-1) {
			ArrayList<String> final_list = new ArrayList<>();
			StringBuilder temp = new StringBuilder();
			
			String num = "";
			for (int i = 0; i < N; i++) {
				if (i == N-1) {
					if (!num.equals("")) {
						num += arr[i];
						final_list.add(num);
					} else {
						final_list.add(arr[i]+"");
					}
					temp.append(arr[i]);
				} else {
					if (ops[i] == ' ') {
						num += arr[i];
					} else {
						final_list.add((num+arr[i]));
						final_list.add(ops[i]+"");
						num = "";
					}
					temp.append(arr[i]).append(ops[i]);
				}
				
			}

			if (final_list.size() >= 3) {
				int n1 = Integer.parseInt(final_list.get(0));
				int n2 = Integer.parseInt(final_list.get(2));
				String op = final_list.get(1);
				
				int result = calc(n1,n2,op);
				
				for (int i = 3; i < final_list.size()-1; i+=2) {
					n2 = Integer.parseInt(final_list.get(i+1));
					op = final_list.get(i);
					result = calc(result, n2 , op);
				}
				
				if (result == 0) {
					list.add(temp.toString());
				}
			}
			
			
			return;
		}
		
		ops[idx] = '+';
		dfs(idx+1);
		
		ops[idx] = '-';
		dfs(idx+1);
		
		ops[idx] = ' ';
		dfs(idx+1);
	}

	private static int calc(int n1, int n2, String op) {
		if (op.equals("+")) {
			return n1+n2;
		} else {
			return n1-n2;
		} 
	}
}
