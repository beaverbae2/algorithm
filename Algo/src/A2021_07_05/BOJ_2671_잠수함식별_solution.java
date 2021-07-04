package A2021_07_05;

import java.util.*;
import java.io.*;

/**
 * regex
 * 
 * @see https://songwonseok.github.io/algorithm/BOJ-2671/
 * @author beaverbae
 *
 */

public class BOJ_2671_잠수함식별_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String regex = "^(100+1+|01)+$";
		
		if (s.matches(regex)) {
			System.out.println("SUBMARINE");
		} else {
			System.out.println("NOISE");
		}
	}

}
