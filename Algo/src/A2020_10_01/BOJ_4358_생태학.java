package A2020_10_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeMap;


public class BOJ_4358_생태학 {
	//static HashMap<String, Integer> trees = new HashMap<>();
	static TreeMap<String, Integer> trees = new TreeMap<>();
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String src = br.readLine();
			if(src==null) break;
			
			if(trees.get(src)==null) {
				trees.put(src, 1);
			}else if(trees.get(src)>0) {
				int nextNum = trees.get(src)+1;
				trees.put(src, nextNum);
			}
			count++;
		}
		
		for(String name : trees.keySet()) {
			double spread = 100.0*(double) (trees.get(name))/count;
			String spread4f = String.format("%.4f", spread);		
			sb.append(name).append(' ').append(spread4f).append('\n');
		}
		System.out.println(sb);
	}
}
