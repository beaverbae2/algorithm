package A2020_12_10;

import java.util.*;

public class PGS_여행경로 {
	static ArrayList<String> list;

	public static String[] solution(String[][] tickets) {
		String[] answer = {};
		list = new ArrayList<>();
		boolean visited[] = new boolean[tickets.length];
		dfs(0, "ICN", visited, tickets);
		Collections.sort(list);
		answer = list.get(0).split(" ");
		return answer;
	}

	private static void dfs(int r,  String road, boolean[] visited, String[][] tickets) {
		if(r==tickets.length) {
			list.add(road);
			return;
		}
		for (int i = 0; i < tickets.length; i++) {
			String check = road.substring(road.length()-3,road.length());
			if(check.equals(tickets[i][0]) && !visited[i]) {
				visited[i] = true;
				dfs(r+1, road+" "+tickets[i][1], visited, tickets);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		String tickets[][] = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
		System.out.println(Arrays.toString(solution(tickets)));
		System.out.println(Arrays.toString(solution(new String[][] {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}})));
		System.out.println(Arrays.toString(solution(new String[][] {{"ICN","A"},{"ICN","B"},{"B","ICN"}})));
	}
}
