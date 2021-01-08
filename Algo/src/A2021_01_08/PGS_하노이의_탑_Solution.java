package A2021_01_08;

import java.util.*;

/**
 * 
 * @author beaverbae
 * @see http://edu.ssafy.com/edu/board/free/detail.do?&brdItmSeq=4945
 * 
 */

//재귀 : flat하게 접근
//번호 출력과 재귀로 어떻게 접근할지 어려웠음
public class PGS_하노이의_탑_Solution {
	private LinkedList<Pair> list = new LinkedList<>();

	public int[][] solution(int n) {
		hanoi(n, 1, 2, 3);
		int[][] answer = new int[list.size()][2];
		int idx = 0;
		for (Pair p : list) {
			answer[idx][0] = p.from;
			answer[idx][1] = p.to;
			idx++;
		}
		
		return answer;
	}

	public void hanoi(int cnt, int from, int temp, int to) {
		if (cnt == 0)
			return;

		// 시작기둥의 cnt-1 덩어리 원판을 임시기둥으로 옮김
		hanoi(cnt - 1, from, to, temp);
		// 시작 기둥의 cnt원판을 목적기등으로 옮김
		list.add(new Pair(from, to));
		// 임시기둥의 cnt-1 덩어리 원판을 목적기둥으로 옮김
		hanoi(cnt - 1, temp, from, to);
	}

	static class Pair {
		int from, to;

		public Pair(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public String toString() {
			return "Pair [from=" + from + ", to=" + to + "]";
		}
	}

	public static void main(String[] args) {
		new PGS_하노이의_탑_Solution().solution(3);
	}
}
