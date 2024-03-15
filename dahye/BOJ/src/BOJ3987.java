import java.io.*;
import java.util.*;

/*
 * 보이저 1호
 */

public class BOJ3987 {
	static char map[][];
	static class Point {
		int r, c, d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	static boolean v[][][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new char[N + 1][M + 1];
		
		for(int r = 1; r < N + 1; r++) {
			String s = br.readLine();
			for(int c = 1; c < M + 1; c++) {
				map[r][c] = s.charAt(c - 1);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		Point tamsa = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		
		int dis = Integer.MIN_VALUE;
		int resultDir = 0;
		
		boolean loop = false;

		L2: for(int d = 0; d < 4; d++) {
			
			v = new boolean[4][N + 1][M + 1];
			
			int r = tamsa.r;
			int c = tamsa.c;
			int distance = 0;
			
			int tmpDir = d;
			v[d][tamsa.r][tamsa.c] = true;
			
			L1: while(true) {
				
				if(map[r][c] == '/') {
					tmpDir = (5 - tmpDir) % 4;
				} else if(map[r][c] == '\\') {
					tmpDir = (3 - tmpDir);
				}
				r += dr[tmpDir];
				c += dc[tmpDir];
				distance++;
				
				if(!check(r, c) || map[r][c] == 'C') {
					if(distance > dis) {
						dis = distance;
						resultDir = d;
					}
					
					break L1;
				}
				if(v[tmpDir][r][c]) {
					loop = true;
					resultDir = d;
					
					break L2;
				}
				v[tmpDir][r][c] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(getDir(resultDir) + "\n");
		sb.append(loop ? "Voyager" : dis);
		
		System.out.println(sb);
	}

	private static char getDir(int d) {
		if(d == 0) return 'U';
		if(d == 1) return 'R';
		if(d == 2) return 'D';
		else return 'L';
	}

	private static boolean check(int r, int c) {
		return r >= 1 && r < map.length && c >= 1 && c < map[0].length;
	}
}
