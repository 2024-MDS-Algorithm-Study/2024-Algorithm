import java.io.*;
import java.util.*;

public class BOJ9466 {
	static int arr[];
	static boolean check[], v[];
	static int res;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();

			int T = Integer.parseInt(br.readLine());

			for(int tc = 0; tc < T; tc++) {
				int n = Integer.parseInt(br.readLine());

			arr = new int[n + 1];
			check = new boolean[n + 1];
			v = new boolean[n + 1];
			res = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i < arr.length; i++) {
				if(check[i]) continue;
				func(i, i);
			}
			
			sb.append(arr.length - 1 - res + " \n");
		}
		
		System.out.println(sb);
	}

	private static void func(int s, int i) {
		if(check[i]) return;
		if(v[i]) {
			check[i] = true;
			res++;
		}
		
		v[i] = true;
		func(s, arr[i]);
		
		check[i] = true;
		v[i] = false;
	}
}