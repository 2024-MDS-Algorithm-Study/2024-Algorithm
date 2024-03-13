import java.io.*;
import java.util.*;

public class BOJ10836 {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		M= Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[2 * M - 1];

		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			for(int j = 1; j <= 2; j++) {
				int num = Integer.parseInt(st.nextToken());
				while(num-- > 0) arr[i++] += j;
			}
		}
		
		for(int r = M - 1; r >= 0; r--) {
			sb.append((arr[r] + 1) + " ");
			for(int c = M; c < 2 * M - 1; c++) {
				sb.append((arr[c] + 1) + " ");
			} sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
