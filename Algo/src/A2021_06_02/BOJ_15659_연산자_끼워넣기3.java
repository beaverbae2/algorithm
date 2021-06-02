package A2021_06_02;

import java.util.*;
import java.io.*;

/**
 * DFS (문자열식 연산)
 * 51MIN
 * 어려웠던 부분
 * - 숫자 범위 잘못 파악 : 움수도 있는줄 알았으나, 각 숫자는 1 ~ 100까지 -> 양수
 * - 변수가 많아지면서 변수들간의 관계가 헷갈렸음
 * @author beaverbae
 *
 */

public class BOJ_15659_연산자_끼워넣기3 {
	static int[] input_nums;
	static int[] ops_cnt; // +, -, x, /
	static char[] real_ops;
	
	static LinkedList<Character> selected_ops;
	static ArrayList<Integer> nums;
	static ArrayList<Character> ops;
	
	static int N;
	static int max, min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input_nums = new int[N];
		ops_cnt = new int[4];
		real_ops = new char[] {'+', '-', '*', '/'};

		selected_ops = new LinkedList<>();
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input_nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < ops_cnt.length; i++) {
			ops_cnt[i] = Integer.parseInt(st.nextToken());
		}
		
		makeOperators(0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void makeOperators(int cnt) {
		if (cnt == N - 1) {
			int result = calc();
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		for (int i = 0; i < ops_cnt.length; i++) {
			if (ops_cnt[i] == 0) continue;
			
			ops_cnt[i]--;
			selected_ops.add(real_ops[i]);
			makeOperators(cnt+1);
			ops_cnt[i]++;
			selected_ops.removeLast();
		}
	}
	
	private static int calc() {
		nums = new ArrayList<>();
		ops = new ArrayList<>();
		
		int idx = 0;
		for (char op : selected_ops) {
			ops.add(op);
			nums.add(input_nums[idx++]);
		}
		nums.add(input_nums[idx]);
		
		calcPrior();
		calcRemain();
		
		return nums.get(0);
	}

	private static void calcPrior() {
		int idx = 0;
		while (idx < ops.size()) {
			char op = ops.get(idx);
			
			if (op == '+' || op == '-') {
				idx++;
				continue;
			}
			
			int n1 = nums.get(idx);
			int n2 = nums.get(idx+1);
		
			int n = operate(op, n1, n2);
			nums.remove(idx);
			nums.add(idx, n);
			nums.remove(idx+1);
		
			ops.remove(idx);
		}
	}
	
	private static void calcRemain() {
		int idx = 0;
		while (idx < ops.size()) {
			char op = ops.get(idx);
			
			if (op == '*' || op == '/') {
				idx++;
				continue;
			}
			
			int n1 = nums.get(idx);
			int n2 = nums.get(idx+1);
		
			int n = operate(op, n1, n2);
			nums.remove(idx);
			nums.add(idx, n);
			nums.remove(idx+1);
		
			ops.remove(idx);
		}
	}

	private static int operate(char op, int n1, int n2) {
		switch (op) {
			case '+':
				return n1 + n2;
			case '-':
				return n1 - n2;
			case '*':
				return n1 * n2;
			case '/':
				return n1 / n2;
			default : 
				return 0;
		}
	}
}
