package A2021_06_14;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 34MIN
 * 어려웠던 부분
 * - 처음에 가장 작은 시간 ~ 큰 시간을 범위로 하여 진행 -> 시간 초과 (가장 큰 시간이 최대 10^9) 
 * - 정렬의 기준 : 강의 시작 시간 인지 종료 시간 인지 -> 둘다 씀 (입력 - 시작시간, 배정 - 종료 시간)
 * 
 * @author beaverbae
 *
 */

public class BOJ_11000_강의실_배정 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		LinkedList<Pair> list = new LinkedList<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.add(new Pair(s, e));
		}
		
		Collections.sort(list, (o1, o2) -> o1.s - o2.s);
		
		int answer = 0;
		for (Pair p : list) {
			while (!pq.isEmpty()) {
				if (pq.peek().e <= p.s) {
					pq.poll();
				} else {
					break;
				}
			}
			pq.add(p);
			answer = Math.max(answer, pq.size());
		}
		
		
		System.out.println(answer);
	}
	
	static class Pair implements Comparable<Pair>{
		int s, e;

		public Pair(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public String toString() {
			return "Pair [s=" + s + ", e=" + e + "]";
		}
		
		@Override
		public int compareTo(Pair o) {
			return this.e - o.e;
		}
	}
}
