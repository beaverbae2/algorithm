package A2021_01_23;

import java.util.*;

public class PGS_여행경로Fail {
	private HashMap<String, Integer> map;
	private HashMap<Integer, String> map2;
	
	private List<Integer>[] graph;
	private HashSet<String> visited;
	private String temp_answer;
	private int N;
	private HashSet<String> total;
	
	public String[] solution(String[][] tickets) {
		map = new HashMap<>();
		map2 = new HashMap<>();
		total = new HashSet<String>();
		int n = 0;
		for (int i = 0; i < tickets.length; i++) {
			int start = -1;
			int end = -1;
			
			if (map.containsKey(tickets[i][0])){
				start = map.get(tickets[i][0]);
			}else {
				start = n;
				map.put(tickets[i][0], start);
				n++;
			}
			
			if (map.containsKey(tickets[i][1])){
				end = map.get(tickets[i][1]);
			}else {
				end = n;
				map.put(tickets[i][1], end);
				n++;
			}
			
		}
		
		Iterator<String> iter = map.keySet().iterator();
		
		while(iter.hasNext()) {
			String value = iter.next();
			int key = map.get(value);
			map2.put(key, value);
		}
		// System.out.println(map);
		graph = new List[map.size()];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		visited = new HashSet<>();
		temp_answer = "";
		
		for (int i = 0; i < tickets.length; i++) {
			int start = map.get(tickets[i][0]);
			int end = map.get(tickets[i][1]);
			
			if (total.contains(start+" "+end)) continue;
			
			graph[start].add(end);
			total.add(start+" "+end);
		}
		N = total.size();
		
		dfs("ICN", 0, map.get("ICN"));
		
		String[] answer = temp_answer.split(" ");
		System.out.println(Arrays.toString(answer));
		return answer;
	}
	
	public void dfs(String path, int cnt, int v) {
		if (cnt == N) {
			if (temp_answer.equals("")) {
				temp_answer = path;
			}else if (temp_answer.compareTo(path) > 0) {
				temp_answer = path;
			}
			System.out.println(temp_answer);
			return;
		}
		
		for (int next_v : graph[v]) {
			if (visited.contains(v+" "+next_v)) continue;
			
			visited.add(v+" "+next_v);
			dfs(path+" "+map2.get(next_v), cnt+1, next_v);
			visited.remove(v+" "+next_v);
		}
	}

	public static void main(String[] args) {
		new PGS_여행경로Fail().solution(new String[][] {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}});
		new PGS_여행경로Fail().solution(new String[][] {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}});
		new PGS_여행경로Fail().solution(new String[][] {{"ICN","A"},{"ICN","B"},{"B","ICN"}});
		new PGS_여행경로Fail().solution(new String[][] {{"ICN", "A"}, {"A", "C"}, {"A", "D"}, {"D", "B"}, {"B", "A"}});
	}
}
