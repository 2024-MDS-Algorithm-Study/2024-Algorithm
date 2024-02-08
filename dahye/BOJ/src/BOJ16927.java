import java.io.*;
import java.util.*;

/*
 * 배열 돌리기 2
 */

public class BOJ16927 {
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {1, 0, -1, 0};
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int R = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int c = 0; c < Math.min(M / 2, N / 2); c++) {
			
			for(int cnt = 0; cnt < R % (2 * (M + N - 2 - 4 * c)); cnt++) {
				int test = 0;
				int nr = c;
				int nc = c;
				
				int currentR = nr;
				int currentC = nc;
				int init = map[c][c];
				
				for(int d = 0; d < 4; d++) {
					
					while(true) {
						nr += dr[d];
						nc += dc[d];
						
						if(!check(nr, nc, c)) {
							if(d == 3) {
								nr -= 2 * dr[d];
								nc -= 2 * dc[d];
								
								map[nr][nc] = init;
							}
							break;
						}
						
						map[currentR][currentC] = map[nr][nc];
						currentR = nr;
						currentC = nc;
					}
					
					nr -= dr[d];
					nc -= dc[d];
				}
			}
		}
		
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				sb.append(map[r][c] + " ");
			} sb.append("\n");
		}
		
		System.out.println(sb);
	}
	private static boolean check(int nr, int nc, int c) {
		return nr - c >= 0 && nr - c < N - 2 * c && nc - c >= 0 && nc - c < M - 2 * c ;
	}
}
