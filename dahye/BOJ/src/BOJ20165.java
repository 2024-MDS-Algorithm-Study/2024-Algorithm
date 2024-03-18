import java.io.*;
import java.util.*;

/*
 * 인내의 도미노 장인 호석
 */

public class BOJ20165 {
	static enum dir {
		E(0, 1), W(0, -1), S(1, 0), N(-1, 0);
		
		int r, c;
		
		dir(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Point {
		int r, c;
		boolean flag;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());		
		int R = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[N + 1][M + 1];
		boolean v[][] = new boolean[N + 1][M + 1];
		
		for(int r = 1; r < N + 1; r++) {
			String s = br.readLine();
			for(int c = 1; c < M + 1; c++) map[r][c] = s.charAt((c - 1) * 2) - '0';
		}
		
		int total = 0;
		
		for(int r  = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			
			Point gong = new Point(stoi(st), stoi(st));
			dir d = dir.valueOf(st.nextToken());
			
			if(v[gong.r][gong.c]) continue;
			else {
				int turn = map[gong.r][gong.c] - 1;
				v[gong.r][gong.c] = true;
				total++;
				
				while(turn > 0) {
					gong.r += d.r;
					gong.c += d.c;
					
					if(!check(gong)) break;
					turn--;
					
					if(v[gong.r][gong.c]) continue;
					v[gong.r][gong.c] = true;
					turn = Math.max(map[gong.r][gong.c] - 1, turn);
					total++;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			v[stoi(st)][stoi(st)] = false;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(total + "\n");
		for(int r = 1; r < v.length; r++) {
			for(int c = 1; c < v[r].length; c++) {
				if(v[r][c]) sb.append("F ");
				else sb.append("S ");
			} sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean check(Point gong) {
		return gong.r >= 1 && gong.r < N + 1 && gong.c >= 1 && gong.c < M + 1;
	}
	private static int stoi(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
