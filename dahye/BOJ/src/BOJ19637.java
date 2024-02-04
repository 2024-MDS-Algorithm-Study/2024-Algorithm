import java.io.*;
import java.util.*;

/*
 * IF문 좀 대신 써줘
 */

public class BOJ19637 {
	static class Chingho {
		String title;
		int power;
		
		public Chingho(String title, int power) {
			this.title = title;
			this.power = power;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Chingho chinghos[] = new Chingho[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			chinghos[i] = new Chingho(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < M; i++) {
			int start = 0;
			int end = N - 1;
			int input = Integer.parseInt(br.readLine());
			
			int mid = 0;
			while(start <= end) {
				mid = (start + end) / 2;
				if(chinghos[mid].power < input) {
					start = mid + 1;
				} else {
					end = mid - 1;
				} 
			}
			
			sb.append(chinghos[start].title + "\n");
		}
		
		System.out.print(sb);
	}
}
