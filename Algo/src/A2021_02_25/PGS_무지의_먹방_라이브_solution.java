package A2021_02_25;

import java.util.*;

/**
 * 
 * @author beaverbae
 * @see J.H.KIM
 */

public class PGS_무지의_먹방_라이브_solution {
	public int solution(int[] food_times, long k) {
		long sum = 0;
		
		LinkedList<Node> foodTimesList = new LinkedList<>();
		for (int i = 0; i < food_times.length; i++) {
			foodTimesList.add(new Node(i+1, food_times[i]));
			sum += food_times[i];
		}
		
		if (sum <= k) return -1;// 다 먹어 버리는 경우
		
		// 시간의 오름차순으로 정렬
		Collections.sort(foodTimesList, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.time, o2.time);
			}
		});
		
		int diff = 0;// 이전의 time
		long n = foodTimesList.size();
		long remove = 0;// 지울수 있는 시간의 길이
		
		while(true) {
			remove = (foodTimesList.get(0).time - diff) * n;// 지울수 있는 시간의 길이
			
			if (k < remove) {// 종료 조건
				break;
			}
			k -= remove;
			diff = foodTimesList.get(0).time;
			foodTimesList.removeFirst();
			n--;
		}
		
		Collections.sort(foodTimesList, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.idx, o2.idx);
			}
		});
		
		k = k % n;
		
		return foodTimesList.get((int) k).idx;
	}
	
	static class Node {
		int idx, time;

		public Node(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", time=" + time + "]";
		}
	}

	public static void main(String[] args) {
		System.out.println(new PGS_무지의_먹방_라이브_solution().solution(new int[] {3,1,2}, 5));
		System.out.println(new PGS_무지의_먹방_라이브_solution().solution(new int[] {1,2,3,1,3}, 8));
//		System.out.println(new PGS_무지의_먹방_라이브().solution(new int[] {1,1,1}, 5));
	}
}
