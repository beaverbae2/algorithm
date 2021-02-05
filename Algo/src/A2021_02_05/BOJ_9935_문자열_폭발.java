package A2021_02_05;

import java.util.*;
import java.io.*;

/**
 * 
 * @author beaverbae
 *
 */

public class BOJ_9935_문자열_폭발 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Pair> list = new LinkedList<>();
		
		String str = br.readLine();
		String target = br.readLine();
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			int idx = getIndex(target, ch);
		
			list.add(new Pair(idx, ch));
			if (list.getLast().idx == target.length() - 1) {
				int index = target.length() - 1;
				Iterator<Pair> iter = list.descendingIterator();
				
				boolean flag = true;
				
				if (list.size() < target.length()) {
					flag = false;
				}else {
					for (int j = 0; j < target.length(); j++) {
						Pair p = iter.next();
						
						if (p.idx == index) index--;
						else {
							flag = false;
							break;
						}
					}
				}
				
				if (flag) {
					for (int j = 0; j < target.length(); j++) {
						list.removeLast();
					}
				}
			}
		}
		
		if (list.isEmpty()) {
			System.out.println("FRULA");
		}else {
			StringBuilder sb = new StringBuilder();
			for(Pair p : list) {
				sb.append(p.ch);
			}
			System.out.println(sb.toString());
		}
	}
	
	private static int getIndex(String target, char ch) {
		for (int i = 0; i < target.length(); i++) {
			if (ch == target.charAt(i)) return i;
		}
		return -1;
	}
	
	static class Pair {
		int idx;
		char ch;
	
		public Pair(int idx, char ch) {
			this.idx = idx;
			this.ch = ch;
		}

		@Override
		public String toString() {
			return "Pair [idx=" + idx + ", ch=" + ch + "]";
		}
		
	}
}
