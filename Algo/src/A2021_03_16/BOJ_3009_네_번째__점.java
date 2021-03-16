package A2021_03_16;

import java.util.*;
import java.io.*;

public class BOJ_3009_네_번째__점 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> map_x = new HashMap<>();
		HashMap<Integer, Integer> map_y = new HashMap<>();
	
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
		
			if (!map_x.containsKey(x)) {
				map_x.put(x, 1);
			} else {
				map_x.put(x, map_x.get(x)+1);
			}
			
			if (!map_y.containsKey(y)) {
				map_y.put(y, 1);
			} else {
				map_y.put(y, map_y.get(y)+1);
			}
		}
		
		int ans_x = 0, ans_y = 0;
		Iterator<Integer> iter = map_x.keySet().iterator();
		while(iter.hasNext()) {
			int key = iter.next();
			int value = map_x.get(key);
			if (value == 1) {
				ans_x = key;
				break;
			}
		}
		
		iter = map_y.keySet().iterator();
		while(iter.hasNext()) {
			int key = iter.next();
			int value = map_y.get(key);
			if (value == 1) {
				ans_y = key;
				break;
			}
		}
		
		System.out.println(ans_x+" "+ans_y);
	}
}
