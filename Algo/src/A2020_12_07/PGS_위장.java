package A2020_12_07;

import java.util.*;

public class PGS_위장 {
	static boolean[] visited;
	static List<String>[] clothes_list;
	
	public static void main(String[] args) {
		//5 3
		System.out.println(solution(new String[][] {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
		System.out.println(solution(new String[][] {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}));	
	}

	public static int solution(String[][] clothes) {
		int num = 0;
		HashMap<String, Integer> kinds = new HashMap<>();
		clothes_list = new List[clothes.length];
		for (int i = 0; i < clothes_list.length; i++) {
			clothes_list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < clothes.length; i++) {
			String name = clothes[i][0];
			String kind = clothes[i][1];
			
			if(kinds.get(kind) == null) {
				kinds.put(kind, num);
				clothes_list[num].add(name);
				num++;
			}else {
				clothes_list[kinds.get(kind)].add(name);
			}
		}
		//visited = new boolean[num];
		//subset(num, 0);
		int answer = 1;
		for (int i = 0; i < num; i++) {
			answer *= clothes_list[i].size()+1;
		}
		answer -=1;
		
		
		return answer;
	}
}	
//	public static void subset(int n, int r) {
//		if(r==n) {
//			int sum = 1;
//			int visited_count = 0;
//			for (int i = 0; i < visited.length; i++) {
//				if(visited[i]) {
//					sum *= clothes_list[i].size();
//					visited_count++;
//				}
//			}
//			if(visited_count>0) answer += sum;
//			return;
//		}
//		
//		visited[r] = true;
//		subset(n, r+1);
//		visited[r] = false;
//		subset(n, r+1);
//	}
	
//}
