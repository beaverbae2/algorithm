package A2020_12_07;

public class SubSet {
	static int[] arr = {1,2,3,4,5};
	static boolean[] visited = new boolean[arr.length];
	
	public static void main(String[] args) {
		subset(0);
	}
	
	private static void subset(int r) {
		if(r==arr.length) {
			for (int i = 0; i < visited.length; i++) {
				if(visited[i]) {
					System.out.print(arr[i]+" ");
				}
			}
			System.out.println();
			return;
		}
		
		visited[r] = true;
		subset(r+1);
		visited[r] = false;
		subset(r+1);
		
	}
	
	
}
