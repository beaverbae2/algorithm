package A2021_09_03;

import java.util.*;
import java.io.*;

/**
 * Regex
 * 15MIN
 * @author beaverbae
 *
 */

public class BOJ_2941_크로아티아_알파벳 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		str = str.replaceAll("dz=|z=|c=|c-|d-|lj|nj|s=", "A");
		System.out.println(str.length());
	}
}
