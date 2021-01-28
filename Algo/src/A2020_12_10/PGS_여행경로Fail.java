package A2020_12_10;

import java.util.*;

public class PGS_여행경로Fail {
	static LinkedList<String> travelRoutesList;
	static HashMap<Integer, String> portMap;
	
	static boolean[][] graph;
	
	public static String[] solution(String[][] tickets) {
        TreeMap<String, Boolean> map = new TreeMap<>();
        HashMap<String, Integer> hm = new HashMap<>();
        portMap = new HashMap<>();
        
        int idx = 0;
        for (int i = 0; i < tickets.length; i++) {
			for (int j = 0; j < tickets[i].length; j++) {
				if(map.get(tickets[i][j]) == null) {
					map.put(tickets[i][j], true);
				}
			}
        }
        
        Iterator<String> iter = map.keySet().iterator();
        idx = 0;
        while(iter.hasNext()) {
        	String airport = iter.next();
        	hm.put(airport, idx);
        	portMap.put(idx, airport);
        	idx++;
        }
        
        int start = hm.get("ICN");
        
        //그래프 생성
        graph = new boolean[hm.size()][hm.size()];//단방향 그래프
        int[] remain_line = new int[hm.size()];
        
        for (int i = 0; i < tickets.length; i++) {
			int a = hm.get(tickets[i][0]);
			int b = hm.get(tickets[i][1]);
			
			graph[a][b] = true;
			remain_line[a]++;
			
		}
        
        travelRoutesList = new LinkedList<>();
        boolean[][] visited = new boolean[hm.size()][hm.size()];
        travelRoutesList.add(portMap.get(start));
        
        dfs(start, visited, remain_line ,true);
        
		
		start = hm.get(travelRoutesList.getLast());
		dfs(start,visited, remain_line, false);
		
		String[] answer = new String[travelRoutesList.size()];
		
		int index = 0;
		for (String route : travelRoutesList) {
			answer[index] = route;
			index++;
		}
        return answer;
    }
	
	private static void dfs(int v, boolean[][] visited, int[] remain_line, boolean flag) {
		for (int i = 0; i < graph[v].length; i++) {
			if(graph[v][i]&&!visited[v][i]) {
				if(flag) {
					if(remain_line[i]>0) {
						travelRoutesList.add(portMap.get(i));
						visited[v][i] = true;
						remain_line[i]--;
						dfs(i, visited, remain_line, flag);
					}
				}else {
					travelRoutesList.add(portMap.get(i));
					visited[v][i] = true;
					remain_line[i]--;
					dfs(i, visited, remain_line, flag);
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[][] {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}})));
		System.out.println(Arrays.toString(solution(new String[][] {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}})));
		System.out.println(Arrays.toString(solution(new String[][] {{"ICN","A"},{"ICN","B"},{"B","ICN"}})));
	}
}
