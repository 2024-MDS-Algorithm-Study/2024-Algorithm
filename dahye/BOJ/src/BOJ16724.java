import java.io.*;
import java.util.*;

/*
 * 피리 부는 사나이
 */

public class BOJ16724 {
	static char map[][];
	static int result, check[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		check = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			
			for(int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c);
			}
		}
		
		int idx = 1;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(check[r][c] != 0) continue;
				flag = true;
				
				func(r, c, r, c, idx);
				idx++;
				
				if(flag) result++;
			}
		}
		
		System.out.println(result);
	}
	
	private static void func(int startR, int startC, int r, int c, int idx) {
		check[r][c] = idx;
		
		int nr = r + dr[dir(map[r][c])];
		int nc = c + dc[dir(map[r][c])];
		
		if(check[nr][nc] != 0) {
			if(check[nr][nc] != idx) flag = false;
			return;
		}
		
		func(startR, startC, nr, nc, idx);                          
	}

	private static int dir(char c) {
		if(c == 'U') return 0;
		else if(c == 'D') return 1;
		else if(c == 'L') return 2;
		else return 3;
	}
}
