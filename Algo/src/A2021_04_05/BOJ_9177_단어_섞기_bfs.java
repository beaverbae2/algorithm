package A2021_04_05;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 방문처리 시점 확인이 까다로움
 * @author beaverbae
 * 
 */

public class BOJ_9177_단어_섞기_bfs {
	static char[] word1, word2, target;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("Data set ").append(tc).append(": ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			word1 = st.nextToken().toCharArray();
			word2 = st.nextToken().toCharArray();
			target = st.nextToken().toCharArray();
			
			if (word1.length + word2.length != target.length) {
				sb.append("no").append("\n");
				continue;
			}
			
			if (bfs()) {
				sb.append("yes").append("\n");
			} else {
				sb.append("no").append("\n");
			}
		}
 		
		System.out.println(sb.toString());
	}

	private static boolean bfs() {
		Queue<Node> q = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();
		q.offer(new Node(0, 0, 0));
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int w1_idx = node.w1_idx;
			int w2_idx = node.w2_idx;
			int t_idx = node.t_idx;
			
			if (t_idx == target.length) {
				return true;
			}
			
			String str = w1_idx+" "+w2_idx+" "+t_idx;// 현재 위치
			if (visited.contains(str)) continue;
			
			// Node값의 의미를 파악하기 -> 일단 큐에 넣고 "다음" 번에 확인해야
			if (w1_idx < word1.length && target[t_idx] == word1[w1_idx]) { 
				q.offer(new Node(w1_idx+1, w2_idx, t_idx+1));// 다음에 확인해야할 위치
				visited.add(str);// 현재 위치 확인 완료
			}
			
			if (w2_idx < word2.length && target[t_idx] == word2[w2_idx]) { 
				q.offer(new Node(w1_idx, w2_idx+1, t_idx+1));// 다음에 확인해야할 위치
				visited.add(str);// 현재 위치 확인 완료
			}
		}
		
		return false;
	}
	
	static class Node {
		int w1_idx, w2_idx, t_idx;

		public Node(int w1_idx, int w2_idx, int t_idx) {
			this.w1_idx = w1_idx;
			this.w2_idx = w2_idx;
			this.t_idx = t_idx;
		}

		@Override
		public String toString() {
			return "Node [w1_idx=" + w1_idx + ", w2_idx=" + w2_idx + ", t_idx=" + t_idx + "]";
		}
	}
}
