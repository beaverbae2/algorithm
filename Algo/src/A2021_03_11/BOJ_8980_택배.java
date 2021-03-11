package A2021_03_11;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * @author beaverbae
 * @see https://steady-coding.tistory.com/58
 * 
 */

public class BOJ_8980_택배 {
	static int N, C, M, answer;
	static int[] maxBoxes;
	static List<Node> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		maxBoxes = new int[N+1];
		for (int i = 1; i < maxBoxes.length; i++) {
			maxBoxes[i] = C;
		}
		
		list = new ArrayList<>();
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int boxes = Integer.parseInt(st.nextToken());
		
			list.add(new Node(start, end, boxes));
		}
		
		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.end != o2.end) { // 도착 지점의 오름차순
					return o1.end - o2.end;
				} else { // 출발 지정믜 오름 차순
					return o1.start - o2.start;
				}
			}
		});
		
		for (Node node : list) {
			int start = node.start;
			int end = node.end;
			int boxes = node.boxes;
			
			int max = Integer.MAX_VALUE;
			for (int i = start; i < end; i++) {
				max = Math.min(max, maxBoxes[i]);
			}
			
			if (max >= boxes) {
				for (int i = start; i < end; i++) {
					maxBoxes[i] -= boxes;
				}
				answer += boxes;
			} else {
				for (int i = start; i < end; i++) {
					maxBoxes[i] -= max;
				}
				answer += max;
			}
		}
		
		System.out.println(answer);
	}
	
	static class Node {
		int start, end, boxes;

		public Node(int start, int end, int boxes) {
			this.start = start;
			this.end = end;
			this.boxes = boxes;
		}

		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + ", boxes=" + boxes + "]";
		}
	}
}
