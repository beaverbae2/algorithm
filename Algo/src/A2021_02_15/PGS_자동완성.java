package A2021_02_15;

import java.util.*;

/**
 * Trie
 * @author beaver
 *
 */

public class PGS_자동완성 {
	public int solution(String[] words) {
		int answer = 0;
		Trie trie = new Trie();
		
		for (String word : words) {
			trie.insert(word);
		}
		
		for (String word : words) {
			answer += trie.getCnt(word);
		}
		
		return answer;
	}
	
	static class Trie {
		Node root;
		
		static class Node {
			HashMap<Character, Node> a = new HashMap<>();
			HashMap<Character, Integer> b = new HashMap<>();
		}
		
		Trie (){
			root = new Node();
		}
		
		public void insert(String word) {
			Node node = root;
			
			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				
				if (!node.a.containsKey(ch)) {
					node.a.put(ch, new Node());
					node.b.put(ch, 1);
				}else {
					int cnt = node.b.get(ch);
					node.b.put(ch, cnt+1);
				}
				
				node = node.a.get(ch);
			}
		}
		
		public int getCnt(String word) {
			Node node = root;
			int cnt = 0;
			
			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
			
				if (node.b.get(ch) == 1) {
					cnt++;
					break;
				}else {
					cnt++;
				}
				
				node = node.a.get(ch);
			}
			
			return cnt;
		}
	}
	

	public static void main(String[] args) {
		System.out.println(new PGS_자동완성().solution(new String[] { "go", "gone", "guild" }));
		System.out.println(new PGS_자동완성().solution(new String[] { "abc", "def", "ghi", "jklm" }));
		System.out.println(new PGS_자동완성().solution(new String[] { "word", "war", "warrior", "world" }));
	}
}
