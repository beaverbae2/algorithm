package A2020_12_14;

import java.util.*;

public class PGS_튜플 {
	public int[] solution(String s) {
		String s_num = "";
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			
			if(ch=='}'||ch=='{'||ch==',') {
				if(!s_num.equals("")) {
					int num = Integer.parseInt(s_num);
					if(map.get(num) == null) {
						map.put(num, 1);
					}else if(map.get(num)>=1) {
						int v = map.get(num);
						map.put(num, v+1);
					}
				}
				s_num = "";
			}else s_num += ch;
		}
		
		List<int[]> list = new ArrayList<>();
		Iterator<Integer> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			int key = iter.next();
			int value = map.get(key);
			list.add(new int[] {key, value});
		}
		
		Collections.sort(list, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[1], o1[1]);
			}
		});
		
		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			int n = list.get(i)[0];
			answer[i] = n;
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		PGS_튜플 a = new PGS_튜플();
		System.out.println(Arrays.toString(a.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
		a = new PGS_튜플();
		System.out.println(Arrays.toString(a.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
		a = new PGS_튜플();
		System.out.println(Arrays.toString(a.solution("{{20,111},{111}}")));
		a = new PGS_튜플();
		System.out.println(Arrays.toString(a.solution("{{123}}")));
		a = new PGS_튜플();
		System.out.println(Arrays.toString(a.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")));
	
	}
}
