package BasicCode;

import java.util.Arrays;

//4C3
public class Combination {
	static char[] arr = {'A','B','C','D'};
	static char[] answer = new char[3];
	static int count;
	
	public static void main(String[] args) {
		combi(0,0);
		System.out.println("전체 개수 : "+count);
	}

	private static void combi(int cnt, int start) {
		if(cnt==3) {
			count++;
			System.out.println(Arrays.toString(answer));
			return;
		}
		
		for (int i = start; i < arr.length; i++) {
			answer[cnt] = arr[i];
			combi(cnt+1, i+1);
		}
	}
}
