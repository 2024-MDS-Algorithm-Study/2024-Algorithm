import java.io.*;
import java.util.*;

/*
 * 덩치
 */

public class BOJ7568 {
	static class Node {
		int w, t;

		public Node(int w, int t) {
			super();
			this.w = w;
			this.t = t;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Node arr[] = new Node[N];
		
		for(int r = 0; r < arr.length; r++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			arr[r] = new Node(w, t);
		}

		for(int r = 0; r < N; r++) {
			int cnt = 0;
			for(int c = 0; c < N; c++) {
				if(c == r) continue;
				if(arr[c].t > arr[r].t && arr[c].w > arr[r].w) {
					cnt++;
				}
			}
			
			System.out.print((cnt + 1) + " ");
		}
	}
}
