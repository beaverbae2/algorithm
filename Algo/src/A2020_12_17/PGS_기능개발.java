package A2020_12_17;

import java.util.*;

public class PGS_기능개발 {
	public int[] solution(int[] progresses, int[] speeds) {
		LinkedList<Integer> progressList = new LinkedList<>();
		LinkedList<Integer> speedList = new LinkedList<>();
		LinkedList<Integer> returnList = new LinkedList<>();

		for (int i = 0; i < speeds.length; i++) {
			progressList.add(progresses[i]);
			speedList.add(speeds[i]);
		}

		while (true) {
			if (progressList.isEmpty())
				break;

			int cnt = 0;
			while (!progressList.isEmpty()) {
				if (progressList.get(0) < 100)
					break;

				else {
					progressList.remove();
					speedList.remove();
					cnt++;
				}
			}

			if (cnt != 0)
				returnList.add(cnt);

			for (int i = 0; i < speedList.size(); i++) {
				int value = progressList.get(i);
				progressList.set(i, value + speedList.get(i));
			}
		}

		int[] answer = new int[returnList.size()];

		int idx = 0;
		for (int value : returnList) {
			answer[idx] = value;
			idx++;
		}

		return answer;
	}

	public static void main(String[] args) {
		PGS_기능개발 a = new PGS_기능개발();
		System.out.println(Arrays.toString(a.solution(new int[] { 93, 30, 55 }, new int[] { 1, 30, 5 })));
		a = new PGS_기능개발();
		System.out.println(
				Arrays.toString(a.solution(new int[] { 95, 90, 99, 99, 80, 99 }, new int[] { 1, 1, 1, 1, 1, 1 })));

	}
}
