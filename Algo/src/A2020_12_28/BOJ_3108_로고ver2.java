package A2020_12_28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3108_로고ver2 {
	static int N;
	static ArrayList<Square> list;
	static boolean visited[];
	static int ans = 0;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			if ((x2 == 0 && (y1 <= 0 && y2 >= 0)) || (x1 == 0 && (y1 <= 0 && y2 >= 0))) {
				flag = true;
			}
			list.add(new Square(x1, y1, x2, y2));
		}
		if (list.get(0).x1 == 0 && list.get(0).y1 == 0 || list.get(0).y1 == 0 && list.get(0).y2 == 0)
			flag = true;
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				BFS(i);
				ans++;
			}
		}
		if (flag)
			System.out.println(ans - 1);
		else
			System.out.println(ans);
	}

	private static void BFS(int s) {
		Queue<Square> q = new LinkedList<>();
		q.add(list.get(s));

		while (!q.isEmpty()) {
			Square temp = q.poll();
			for (int i = s + 1; i < list.size(); i++) {
				Square compare = list.get(i);
				if (isOK(temp, compare) && !visited[i]) {
					q.add(compare);
					visited[i] = true;
				}
			}
		}

	}

	private static boolean isOK(Square temp, Square compare) {
		if (isIn(temp, compare) || isOut(temp, compare))
			return false;
		else
			return true;
	}

	private static boolean isIn(Square c, Square n) {

		return (c.x1 < n.x1 && n.x2 < c.x2 && c.y1 < n.y1 && n.y2 < c.y2)
				|| (c.x1 > n.x1 && n.x2 > c.x2 && c.y1 > n.y1 && n.y2 > c.y2);
	}

	private static boolean isOut(Square temp, Square compare) {
		return temp.x2 < compare.x1 || temp.x1 > compare.x2 || temp.y2 < compare.y1 || temp.y1 > compare.y2;
	}

	static class Square {
		int x1;
		int y1;
		int x2;
		int y2;

		public Square(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

	}

}