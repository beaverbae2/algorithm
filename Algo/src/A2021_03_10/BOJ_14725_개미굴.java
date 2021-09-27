package A2021_03_10;

import java.util.*;
import java.io.*;

/**
 * Trie
 * 25MIN
 * @author beaverbae
 *
 */

public class BOJ_14725_개미굴 {
	static StringBuilder sb;
	static final String depth = "--"; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		Trie trie = new Trie();
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			String[] names = new String[K];
			
			for (int k = 0; k < K; k++) {
				names[k] = st.nextToken();
			}
			trie.insert(names);
		}
		
		trie.preOrder(trie.root, 0);
		System.out.println(sb.toString());
	}
	
	
	static class Trie {
		Node root;
		
		static class Node {
			TreeMap<String, Node> map;

			public Node() {
				this.map = new TreeMap<>();
			}
		}
		
		public Trie() {
			root = new Node();
		}
		
		public void insert(String[] names) {
			Node node = root;
			
			for (String name : names) {
				
				if (!node.map.containsKey(name)) {
					node.map.put(name, new Node());
				}
				node = node.map.get(name);
			}
		}
		
		public void preOrder(Node node, int cnt) {
			Iterator<String> iter = node.map.keySet().iterator();
			StringBuilder temp_sb = new StringBuilder();
			for (int i = 0; i < cnt; i++) {
				temp_sb.append(depth);
			}
			while(iter.hasNext()) {
				String name = iter.next();
				sb.append(temp_sb).append(name).append("\n");
				preOrder(node.map.get(name),cnt+1);
			}
		}
	}
}
