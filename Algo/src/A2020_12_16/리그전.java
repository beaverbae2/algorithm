package A2020_12_16;

import java.io.*;
import java.util.*;

public class 리그전 {
	static int N;
	static int[] arr;
	static int[] jumsu;
	static boolean flag;
	static LinkedList<Integer> tuple;
	static LinkedList<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		jumsu = new int[N];
		flag = false;
		list = new LinkedList<>();
		tuple = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0);
		check(0, list.size()/2);
		
		
		if(flag) System.out.println("O");
		else System.out.println("X");
	}
	
	//점수 구하기
	private static void check(int r, int n) {
		if (r == n) {
			if(isSame()) flag = true;
			return;
		}
		
		int first = list.get(2*r);
		int last = list.get(2*r+1);
		
		jumsu[first]++;
		check(r+1,n);
		jumsu[first]--;
		jumsu[last]++;
		check(r+1,n);
		jumsu[last]--;
	}

	//조합으로 대결하는 2개 쌍 고르기
	private static void dfs(int start, int r) {
		if(r==2) {
			list.addAll(tuple);
			return;
		}
		
		for (int i = start; i < N; i++) {
			tuple.add(i);
			dfs(i+1, r+1);
			tuple.removeLast();
		}
	}

	private static boolean isSame() {
		for (int i = 0; i < N; i++) {
			if(arr[i] != jumsu[i]) return false;
		}
		return true;
	}
}
