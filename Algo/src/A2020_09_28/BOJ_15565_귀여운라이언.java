package A2020_09_28;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15565_귀여운라이언 {
	static int[] dolls;
	static int N,K;
	static int answer = 1000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dolls = new int[N];
		
		st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			int doll = Integer.parseInt(st.nextToken());
			
			if(doll==2) continue;
			
			q.offer(i);
			if(q.size()==K) {
				int range = i - q.poll()+1;
				answer = Math.min(answer, range);
			}
		}
		if(answer == 1000000) System.out.println(-1);
		else System.out.println(answer);
	}
}
