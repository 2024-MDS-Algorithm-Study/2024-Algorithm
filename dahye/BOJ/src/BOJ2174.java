import java.io.*;
import java.util.*;

/*
 * 로봇 시뮬레이션
 */

public class BOJ2174 {
	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	static int map[][];
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	static HashMap<Integer, Robot> hashMap = new HashMap<Integer, Robot>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		map = new int[B + 1][A + 1];
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			
			map[r][c] = i + 1;
			
			hashMap.put(i + 1, new Robot(r, c, dir(d)));
		}

		for(int tc= 1; tc < M + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken()); // 명령을 내리는 로봇
			char ch = st.nextToken().charAt(0);
			int num = Integer.parseInt(st.nextToken());
			
			// 벽에 부딪혔을 경우
			if(!func(idx, ch, num)) return;
		}
		
		System.out.println("OK");
	}

	private static boolean func(int idx, char ch, int num) {
		
		while(num-- > 0) {
			Robot current = hashMap.get(idx);
			if(ch == 'F') {
				int nr = current.r + dr[current.d];
				int nc = current.c + dc[current.d];
				
				if(!check(nr, nc)) {
					System.out.printf("Robot %d crashes into the wall", idx);
					return false;
				}
				
				int next = existRobot(nr, nc);
				if(next != 0) {
					System.out.printf("Robot %d crashes into robot %d", idx, next);
					return false;
				}
				
				map[current.r][current.c] = 0;
				current.r = nr;
				current.c = nc;
				
				map[nr][nc] = idx;
			} else if(ch == 'L') {
				current.d = (current.d + 1) % 4;
			} else if(ch == 'R') {
				current.d = current.d - 1 < 0 ? current.d - 1 + 4: current.d - 1;
			}
		}
		return true;
	}

	private static int existRobot(int nr, int nc) {
		return map[nr][nc];
	}

	private static boolean check(int nr, int nc) {
		return nr >= 1 && nr < map.length && nc >= 1 && nc < map[0].length;
	}

	private static int dir(char d) {
		if(d == 'S') return 0;
		else if(d == 'E') return 1;
		else if(d == 'N') return 2;
		else return 3;
	}
}
