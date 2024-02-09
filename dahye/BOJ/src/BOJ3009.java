import java.io.*;
import java.util.*;

/*
 * 네 번째 점
 */

public class BOJ3009 {
	static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		HashMap<Integer, Integer> x = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> y = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			func(x, a);
			func(y, b);
		}
		
		System.out.println(find(x) + " " + find(y));
	}

	private static int find(HashMap<Integer, Integer> x) {
		x.entrySet().forEach(s -> {
			if(s.getValue() == 1) {
				n = s.getKey();
			}
		});
		return n;
	}

	private static void func(HashMap<Integer, Integer> x, int a) {
		if(!x.containsKey(a)) x.put(a, 1);
		else x.put(a, x.get(a) + 1);
	}
}
