import java.io.*;
import java.util.*;

/*
 * 확장 게임
 */

public class BOJ16920 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int len[], count[];
	static char map[][];
	static Queue<Point> queue[] = new LinkedList[10];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int cnt = 0;

		map = new char[N][M];
		len = new int[P + 1];
		count = new int[P + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < len.length; i++) len[i] = Integer.parseInt(st.nextToken());
		for(int i = 0; i < 10; i++) queue[i] = new LinkedList<Point>();
		
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			
			for(int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c);
				if(map[r][c] == '.') cnt++;
				if(checkNumRange(map[r][c])) {
					queue[charToNum(map[r][c])].add(new Point(r, c));
					count[charToNum(map[r][c])]++;
				}
			}
		}
		
		while(cnt > 0) {
			boolean flag = false;
			for(int i = 0; i < queue.length; i++) {
				if(queue[i].size() > 0) flag = true;
			}
			
			if(!flag) break;
			
			// 순서대로 
			for(int num = 1; num < P + 1; num++) {
				int l = len[num];
				
				while(!queue[num].isEmpty() && l-- > 0) {
					int size = queue[num].size();
					
					while(size-- > 0) {
						Point current = queue[num].poll();
						for(int d = 0; d < 4; d++) {
							int nr = current.r + dr[d];
							int nc = current.c + dc[d];
							
							if(!check(nr, nc)) continue;
							if(map[nr][nc] == '.') {
								queue[num].add(new Point(nr, nc));
								map[nr][nc] = map[current.r][current.c];
								count[num]++;
								cnt--;
							}
						}
					}
				}
			}
		}

		for(int i = 1; i < P + 1; i++) {
			sb.append(count[i] + " ");
		}
		
		System.out.println(sb);
	}

	private static boolean checkNumRange(char c) {
		return charToNum(c) > 0 && charToNum(c) < 10;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
	}

	private static int charToNum(char c) {
		return c - '0';
	}
}