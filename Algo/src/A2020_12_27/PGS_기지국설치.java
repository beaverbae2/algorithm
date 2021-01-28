package A2020_12_27;

public class PGS_기지국설치 {
	public int solution(int n, int[] stations, int w) {
		int answer = 0;

		int idx = 0;
		int start = 1;

		while (start <= n) {
			if (idx < stations.length && start >= stations[idx] - w) {
				start = stations[idx] + w + 1;
				idx++;
			} else {
				answer++;
				start += 2 * w + 1;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		System.out.println(new PGS_기지국설치().solution(11, new int[] { 4, 11 }, 1));
		System.out.println(new PGS_기지국설치().solution(16, new int[] { 9 }, 2));
	}
}
