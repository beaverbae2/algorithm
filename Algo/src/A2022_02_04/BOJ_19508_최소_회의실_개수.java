package A2022_02_04;

import java.util.*;
import java.io.*;

/**
 * Greedy, Sorting, Heap
 * 55MIN
 * @author beaverbae
 * - 정렬 기준 파악이 어려웠음
 */

public class BOJ_19508_최소_회의실_개수 {
	static LinkedList<Pair> times;
	static int N;
	
	public static void main(String[] args) throws Exception {
		setUp();
		sort();
		System.out.println(calc());
	}
	
	private static void setUp() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		times = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			times.add(new Pair(s, e));
		}
	}
	
	private static void sort() {
		Collections.sort(times, (Pair o1, Pair o2) -> { 
			if (o1.s != o2.s) return o1.s - o2.s;
			return o1.e - o2.e;
		});
	}
	
	private static int calc() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(times.poll().e);
		
		while (!times.isEmpty()) {
			Pair p = times.poll();
			int e = pq.peek();
			
			if (e <= p.s) {
				pq.poll();
			}
			
			pq.add(p.e);
		}
		
		return pq.size();
	}
	
	static class Pair {
		int s, e;
		
		public Pair(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public String toString() {
			return "[s=" + s + ", e=" + e + "]";
		}
	}
}
