package A2020_12_17;

import java.util.*;

public class PGS_야근지수 {
	PriorityQueue<Integer> pq;

	public long solution(int n, int[] works) {
		pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer n1, Integer n2) {
				return Integer.compare(n2, n1);
			}
		});

		for (int i = 0; i < works.length; i++) {
			pq.add(works[i]);
		}

		while (n > 0) {
			int max = pq.peek();
			if (max == 0)
				break;
			pq.add(max - 1);
			pq.poll();
			n--;
		}
		long answer = 0;

		while (!pq.isEmpty()) {
			answer += (long) Math.pow(pq.poll(), 2);
		}

		return answer;
	}

	public static void main(String[] args) {
		PGS_야근지수 a = new PGS_야근지수();
		System.out.println(a.solution(4, new int[] { 4, 3, 3 }));
		a = new PGS_야근지수();
		System.out.println(a.solution(1, new int[] { 2, 1, 2 }));
		a = new PGS_야근지수();
		System.out.println(a.solution(3, new int[] { 1, 1 }));
	}
}
