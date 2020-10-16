package BasicCode;

//요소가 4개일떄 부분 집합 구하기 -> 2^4 == 16
public class Subset {
	static char[] arr = {'A','B','C','D'};
	static boolean[] visited = new boolean[arr.length];
	
	public static void main(String[] args) {
		getSubset(0);
	}

	private static void getSubset(int cnt) {
		if(cnt==arr.length) {
			StringBuilder sb = new StringBuilder();
			//방문 처리 해준 것만 출력
			for (int i = 0; i < visited.length; i++) {
				if(!visited[i]) continue;
				sb.append(arr[i]).append(' ');
			}
			System.out.println(sb);
			return;
		}
		
		visited[cnt] = false;
		getSubset(cnt+1);
		visited[cnt] = true;
		getSubset(cnt+1);
	}
}
