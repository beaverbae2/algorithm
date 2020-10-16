package BasicCode;

import java.util.Arrays;

//4P3
public class Permutation {
	static char[] arr = {'A','B','C','D'};
	static char[] answer = new char[3];
	static boolean[] visited = new boolean[arr.length];
	static int count = 0;
	
	public static void main(String[] args) {
		perm(0);
		System.out.println("전체: "+count);
	}

	private static void perm(int cnt) {
		if(cnt==3) {
			count++;
			System.out.println(Arrays.toString(answer));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(visited[i]) continue;
		
			visited[i] = true;
			answer[cnt] = arr[i];
			perm(cnt+1);
			visited[i] = false;
		}
	}
}
