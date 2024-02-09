import java.io.*;
import java.util.*;

/*
 * Two Dots
 */

public class BOJ16929 {
	static char map[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static boolean v[][];
	static boolean check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		String result = "No";
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			
			map[r] = s.toCharArray();
		}

		L: for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				check = false;
				
				v = new boolean[N][M];
				v[r][c] = true;
				
				func(r, c, r, c, map[r][c], 1);
				
				if(check) {
					result = "Yes";
					break L;
				}
			}
		}
		
		System.out.println(result);
	}

	private static void func(int startR, int startC, int r, int c, char ch, int cnt) {
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr, nc) || map[nr][nc] != ch) continue;
			if(startR == nr && startC == nc && cnt > 3) {
				check = true;
				return;
			}
			
			if(v[nr][nc]) continue;
			v[nr][nc] = true;
			func(startR, startC, nr, nc, ch, cnt + 1);
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}
}
