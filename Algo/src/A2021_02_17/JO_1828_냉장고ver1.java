package A2021_02_17;

import java.util.*;
import java.io.*;

public class JO_1828_냉장고ver1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Pair> stack = new Stack<>();
		int answer = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int min = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			
			stack.add(new Pair(min, max));
		}
		
		// 최댓값에 대해서 내림차순 정렬(stack이라서 거꾸로 해야함)
		Collections.sort(stack, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return Integer.compare(o2.max, o1.max);
			}
		});
		
		while(!stack.isEmpty()) {
			Pair top = stack.pop();

			while(!stack.isEmpty()) {
				// 다음 화학물질의 최솟값이 top의 최댓값보다 작거나 같은 경우 pop
				if (stack.peek().min <= top.max) stack.pop();
				else break;
			}
			answer++;
		}
		System.out.println(answer);
	}
	
	static class Pair {
		int min, max;

		public Pair(int min, int max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public String toString() {
			return "[" + min + ", "+ max + "]";
		}
	}
}
