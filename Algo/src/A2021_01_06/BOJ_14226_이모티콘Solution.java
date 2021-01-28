package A2021_01_06;

import java.util.*;
import java.io.*;

/**
 * 
 * @author beaverbae
 * @see https://jaejin89.tistory.com/45
 * 
 */

public class BOJ_14226_이모티콘Solution {
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		
		visited = new boolean[1001][1001];//이모티콘 개수, 복사된 이모티콘 개수
		
		System.out.println(bfs(S));
	}
	
	private static int bfs(int S) {
		//초기화
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(1, 0, 0));
//		visited[1] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			int len = node.len;
			int buf = node.buf;
			int cnt = node.cnt;
			
			if (len == S) {
				return cnt;
			}
			
			//복사
			q.offer(new Node(len, len, cnt+1));
			
			//붙여넣기
			if(buf>0 && len+buf<=1000 && !visited[len+buf][buf]) {
				q.offer(new Node(len+buf, buf, cnt+1));
				visited[len+buf][buf] = true;
			}
			
			//한 개 삭제
			if(len-1>0 && len-1<=1000 && !visited[len-1][buf]) {
				q.offer(new Node(len-1, buf, cnt+1));
				visited[len-1][buf] = true;
			}
			
		}
		
		return 0;
	}

	static class Node {
		int len, buf, cnt;//이모티콘 개수, 복사된 개수, 연산 개수 

		public Node(int len, int buf, int cnt) {
			this.len = len;
			this.buf = buf;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [len=" + len + ", buf=" + buf + ", cnt=" + cnt + "]";
		}
	}
}
