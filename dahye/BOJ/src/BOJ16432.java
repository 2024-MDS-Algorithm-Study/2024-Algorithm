import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ16432 {
	static ArrayList<Integer> list[];
	static boolean v[][];
	static boolean check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		v = new boolean[N + 1][10];
		
		for(int i = 0; i < list.length; i++) list[i] = new ArrayList<Integer>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken());
			for(int j = 0; j < M; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int start: list[0]) {
			dfs(0, -1, start, N, new int[N]);
		}
		
		if(!check) System.out.println(-1);
	}

	private static void dfs(int depth, int yester, int today, int N, int[] arr) {
		arr[depth] = today;
		
		if(!check && depth == N - 1) {
			for(int a: arr) System.out.println(a);
			check = true;
			return;
		}

		for(int next: list[depth + 1]) {
			if(next == today || v[depth + 1][next]) continue;
			v[depth + 1][next] = true;
			dfs(depth + 1, today, next, N, arr);
		}
	}
}