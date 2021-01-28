package A2021_01_07;

import java.util.*;
import java.io.*;

public class BOJ_1248_맞춰봐Fail {
	static char[][] S;
	static int[] A;
	static int N;
	static HashMap<String, Boolean> dupCheckMap;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new char[N][N];
		A = new int[N];
		dupCheckMap = new HashMap<>();
		
		String src = br.readLine();
		int n = N;
		int r = 0, c = 0, cnt = 0;
		int end = src.length()-N;
		
		//S배열 입력 받기
		sb = new StringBuilder();
		for (int i = 0; i < src.length(); i++) {
			char ch = src.charAt(i);
			S[r][c] = ch;
			
			if(r == c) {
				if (S[r][c] == '-') {
					A[r] = -1;
				}else if (S[r][c] == '+') {
					A[r] = 1;
				}else {
					A[r] = 0;
				}
				sb.append(A[r]).append(" ");
			}
			
			c++;
			cnt++;
			
			if (cnt == n) {
				n--;
				r++;
				c = r;
				cnt = 0;
			}
		}
		
		dupCheckMap.put(sb.toString(), true);
		boolean flag = true;
		while(true) {
			int temp_end = 0;
			flag = false;
			
			for (int i = 0; i < S.length; i++) {
				int temp_sum = A[i];
				for (int j = i+1; j < S[i].length; j++) {
					temp_sum += A[j];
					
					if (check(temp_sum, i, j)) {
						flag = true;
						temp_end++;
					}else {
						flag = false;
						changeNum(temp_sum,i, j);
					}
					
					if (!flag) break;
				}
				if (!flag) break;
			}
			
			if(temp_end == end) break;
		}
		
		sb = new StringBuilder();
		for (int i = 0; i < A.length; i++) {
			sb.append(A[i]).append(" ");
		}
		System.out.println(sb);
		
	}
	
	private static boolean check(int temp_sum, int i, int j) {
		if (temp_sum > 0 && S[i][j] == '+') {
			return true;
		}else if (temp_sum < 0 && S[i][j] == '-') {
			return true;
		}else if (temp_sum == 0 && S[i][j] == '0') {
			return true;
		}
		return false;
	}
	
	private static void changeNum(int temp_sum, int i, int j) {
		if (S[i][j] == '+') {
			for (int k = i; k <= j; k++) {
				if (A[k] >=1 && A[k] <=9) {
					A[k]++;
					
					if (isDupNumbers()) {
						A[k]--;
					}
					else {
						dupCheckMap.put(sb.toString(), true);
						break;
					}
				}else if (A[k] <=-2 && A[k] >= -10) {
					A[k]++;
					
					if (isDupNumbers()) {
						A[k]--;
					}
					else {
						dupCheckMap.put(sb.toString(), true);
						break;
					}
				}
			}
		}else if(S[i][j] == '-') {
			for (int k = i; k <= j; k++) {
				if (A[k] >=2 && A[k] <=10) {
					A[k]--;
					
					if (isDupNumbers()) {
						A[k]++;
					}
					else {
						dupCheckMap.put(sb.toString(), true);
						break;
					}
				}else if (A[k] <=-1 && A[k] >= -9) {
					A[k]--;
					
					if (isDupNumbers()) {
						A[k]++;
					}
					else {
						dupCheckMap.put(sb.toString(), true);
						break;
					}
				}
			}
		}else {
			
			if (temp_sum < 0) {
				for (int k = i; k <= j; k++) {
					if (A[k] >=1 && A[k] <=9) {
						A[k]++;
						
						if(isSumZero(i, j)&&!isDupNumbers()) {
							dupCheckMap.put(sb.toString(), true);
							break;
						}else A[k]--;
						
					}else if (A[k] <=-2 && A[k] >= -10) {
						A[k]++;
						
						if(isSumZero(i, j)&&!isDupNumbers()) {
							dupCheckMap.put(sb.toString(), true);
							break;
						}else A[k]--;
						
					}
				}
			}else {
				for (int k = i; k <= j; k++) {
					if (A[k] >=2 && A[k] <=10) {
						A[k]--;
						
						if(isSumZero(i,j)&&!isDupNumbers()) {
							dupCheckMap.put(sb.toString(), true);
							break;
						}else A[k]++;
						
					}else if (A[k] <=-1 && A[k] >= -9) {
						A[k]--;
						
						if(isSumZero(i,j)&&!isDupNumbers()) {
							dupCheckMap.put(sb.toString(), true);
							break;
						}else A[k]++;
						
					}
				}
			}
			
		}
	}
	
	private static boolean isDupNumbers() {
		sb = new StringBuilder();
		for (int l = 0; l < A.length; l++) {
			sb.append(A[l]).append(" ");
		}
		
		if(dupCheckMap.get(sb.toString()) == null) {
			return false;
		}
		
		return true;
	}
	
	private static boolean isSumZero(int s, int e) {
		int sum = 0;
		for (int i = s; i <= e; i++) {
			sum += A[i];
		}
		if (sum == 0) return true;
		return false;
	}
}
