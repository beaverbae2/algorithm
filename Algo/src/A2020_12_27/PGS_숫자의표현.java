package A2020_12_27;

public class PGS_숫자의표현 {
	public int solution(int n) {
		int answer = 0;

		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = i; j <= n; j++) {
				sum += j;
				if (sum > n)
					break;
				else if (sum == n) {
					answer++;
					break;
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		PGS_숫자의표현 a = new PGS_숫자의표현();
		System.out.println(a.solution(15));
	}
}
