package A2020_12_09;

public class Test {
	public static void main(String[] args) {
		int N = 5;
		for (int i = 1; i <= 2*N; i++) {
			int j = (-1) * i;
			System.out.println(j%N);
		}
	}
}
