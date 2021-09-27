package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 34MIN
 * @author beaverbae
 * 가방 하나에 보석 하나만 들어가는것이 핵심
 * 가방 무게 이하의 보석들 중에 가치가 가장 높은 것을 선택 -> 구현이 생각보다 까다로웠다
 */

public class BOJ_1202_보석_도욱 {
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Node> inputs = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int[] bags;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		bags = new int[K]
				;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			inputs.add(new Node(w, v));
		}
		
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);
		
		long ans = 0;
		for (int bag : bags) {
			while (!inputs.isEmpty()) {
				if (inputs.peek().w > bag) break;
				
				pq.add(inputs.poll().v);
			}
			
			if (pq.peek() == null) continue;
			ans += (long) pq.poll();
		}
		
		System.out.println(ans);
	}
	
	static class Node {
		int w, v;

		public Node(int w, int v) {
			this.w = w;
			this.v = v;
		}

		@Override
		public String toString() {
			return "Node [w=" + w + ", v=" + v + "]";
		}
	}
}
