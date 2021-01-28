package A2021_01_25;

/**
 * Parametric Search
 * 
 * @author beaverbae
 * @see https://bcp0109.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-2019-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EA%B2%A8%EC%9A%B8%EC%9D%B8%ED%84%B4-%EC%A7%95%EA%B2%80%EB%8B%A4%EB%A6%AC-%EA%B1%B4%EB%84%88%EA%B8%B0-Java
 *
 */
public class PGS_징검다리_건너기 {
	private int[] stones;
	private int k;

	public int solution(int[] stones, int k) {
		int answer = 0;
		int end = Integer.MIN_VALUE;
		int start = Integer.MAX_VALUE;
		this.stones = stones;
		this.k = k;

		// stone의 최댓값, 최솟값 구하기
		for (int stone : stones) {
			end = Math.max(end, stone);
			start = Math.min(start, stone);
		}

		while (start <= end) {
			int mid = (start + end) / 2; // 건널 수 있는 친구의 수

			if (canGo(mid)) {
				answer = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		System.out.println(answer);
		return answer;
	}

	public boolean canGo(int friends) {
		int cnt = 0; // 연속해서 건널 수 없는 돌의 개수
		for (int stone : stones) {
			if (stone - friends < 0) {
				cnt++;
			} else {
				cnt = 0;
			}

			if (cnt == k)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new PGS_징검다리_건너기().solution(new int[] { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 }, 3);

	}
}
