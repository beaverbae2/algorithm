package A2021_01_05;

import java.util.*;

public class PGS_스타_수열Fail {
	private LinkedList<Integer> list;
	
	public int solution(int[] a) {
        int answer = 0;
		
        list = new LinkedList<>();
    	for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
        
        if(a.length%2==0 && a.length>0) {
			answer = getAnswer();
        }else {
        	for (int i = 0; i < a.length; i++) {
        		list.remove(i);
        		answer = getAnswer();
                list.add(i, a[i]);
        	}
        }
		
        return answer;
    }
	
	private int getAnswer() {
		HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < list.size(); i+=2) {
			int n1 = list.get(i-1);
			int n2 = list.get(i);
			
			if(n1 != n2) {
				if(map.get(n1) == null) {
					map.put(n1, 2);
				}else {
					int value = map.get(n1);
					map.put(n1, value+2);
				}
				
				if(map.get(n2) == null) {
					map.put(n2, 2);
				}else {
					int value = map.get(n2);
					map.put(n2, value+2);
				}
				
			}
		}
        
    	Iterator<Integer> iter = map.keySet().iterator();
        LinkedList<Integer> list = new LinkedList<>();
        
        while(iter.hasNext()) {
        	int key = iter.next();
        	list.add(map.get(key));
        }
        
        Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		});
		
        int result = 0;
        if(!list.isEmpty()) result = list.get(0);
        return result;
	}

	public static void main(String[] args) {
		System.out.println(new PGS_스타_수열Fail().solution(new int[] {0}));
		System.out.println(new PGS_스타_수열Fail().solution(new int[] {5,2,3,3,5,3}));
		System.out.println(new PGS_스타_수열Fail().solution(new int[] {0,3,3,0,7,2,0,2,2,0}));
		System.out.println(new PGS_스타_수열Fail().solution(new int[] {1,2,3,4,5,1,3}));
	}
}
