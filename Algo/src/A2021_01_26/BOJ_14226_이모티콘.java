package A2021_01_26;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 
 * @author beaverbae
 *
 */
public class BOJ_14226_이모티콘 {
	static int S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());

		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();// 방문 체크 용도
		q.offer(new Node(1, 0, 0));
		visited.add(1 + " " + 0);

		while (!q.isEmpty()) {
			Node node = q.poll();
			int window = node.window;
			int clip = node.clip;
			int cnt = node.cnt;

			// 화면에서 복사
			if (window > 0 && !visited.contains(window + " " + window)) {
				visited.add(window + " " + window);
				q.offer(new Node(window, window, cnt + 1));
			}

			// 클립보드에 복사된 것 붙여넣기
			if (clip > 0 && !visited.contains((window + clip) + " " + window)) {
				if (window + clip == S) {
					return cnt + 1;
				}
				visited.add((window + clip) + " " + clip);
				q.offer(new Node(window + clip, clip, cnt + 1));
			}

			// 화면에서 하나 삭제
			if (window > 0 && !visited.contains((window - 1) + " " + clip)) {
				if (window - 1 == S) {
					return cnt + 1;
				}
				visited.add((window - 1) + " " + clip);
				q.offer(new Node(window - 1, clip, cnt + 1));
			}
		}

		return -1;
	}

	static class Node {// Queue의 요소
		int window, clip, cnt;

		public Node(int window, int clip, int cnt) {
			this.window = window;
			this.clip = clip;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [window=" + window + ", clip=" + clip + ", cnt=" + cnt + "]";
		}
	}
}
