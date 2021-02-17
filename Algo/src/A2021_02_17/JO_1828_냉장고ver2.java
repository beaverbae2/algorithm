package A2021_02_17;

import java.util.PriorityQueue;
import java.util.Scanner;

public class JO_1828_냉장고ver2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		PriorityQueue<Chemical> pq = new PriorityQueue<>();

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			Chemical tmp = new Chemical(sc.nextInt(), sc.nextInt());
			pq.add(tmp);
		}

		while (!pq.isEmpty()) {
			Chemical tmp = pq.poll();
			while (!pq.isEmpty()) {
				Chemical next = pq.peek();
				if (tmp.high < next.low)
					break;// 범위 벗어나는 경우

				else if (next.low >= tmp.low) {
					tmp.low = next.low;

					if (next.high <= tmp.high) {
						tmp.high = next.high;
					}
					pq.poll();
				}
			}
			cnt++;

		}

		System.out.println(cnt);
	}

	public static class Chemical implements Comparable<Chemical> {
		int low; // 최저온도
		int high; // 최고온도

		Chemical(int low, int high) {
			this.low = low;
			this.high = high;
		}

		@Override
		public int compareTo(Chemical o) {
			return Integer.compare(this.low, o.low);
		}
	}
}
