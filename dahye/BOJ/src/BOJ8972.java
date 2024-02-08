import java.io.*;
import java.util.*;

/* 
 * 미친 아두이노
 */

public class BOJ8972 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static int dr[] = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int dc[] = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	static int R, C;
	static HashMap<Integer, Point> hashMap;
	static ArrayList<Integer> num[][];
	static Point jongsu;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		num = new ArrayList[R][C];
		hashMap = new HashMap<Integer, Point>();
		for(int r = 0; r < R; r++) for(int c = 0; c < C; c++) num[r][c] = new ArrayList<Integer>();
		
		int idx = 0;
		for(int r = 0; r < R; r++) {
			String s = br.readLine();
			
			for(int c = 0; c < C; c++) {
				int input = s.charAt(c);
				if(input == 'I') jongsu = new Point(r, c);
				if(input == 'R') {
					hashMap.put(idx, new Point(r, c));
					num[r][c].add(idx);
					idx++;
				}
			}
		}
		
		String s = br.readLine();
		
		int test_case = 0;
		boolean check = true;
		
		while(test_case < s.length()) {
			int d = s.charAt(test_case) - '0';
			
			// 종수 움직이기
			if(!move(d) || !moveMichin()) {
				check = false;
				break;
			}
			
			test_case++;
		}
		
		if(!check) sb.append("kraj " + (test_case + 1));
		else {
			for(int r = 0; r < num.length; r++) {
				for(int c = 0; c < num[r].length; c++) {
					if(num[r][c].size() == 0) {
						if(r == jongsu.r && c == jongsu.c) sb.append("I");
						else sb.append(".");
					} else {
						sb.append("R");
					}
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

	private static boolean moveMichin() {
		list.clear();
		flag = true;
		
		// 종수의 위치와 가까워 지는 방향으로 이동
		hashMap.entrySet().forEach(m -> {
			int dis = Integer.MAX_VALUE;
			int idx = 0;
			int nr = 0;
			int nc = 0;
			
			for(int d = 1; d < 10; d++) {
				// 종수와 위치 구해서 가장 가까운걸로
				nr = m.getValue().r + dr[d];
				nc = m.getValue().c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(dis > calDis(nr, nc)) {
					dis = calDis(nr, nc);
					idx = d;
				}
			}

			// 아두이노 움직이기 (원래 위치에서 삭제)
			num[m.getValue().r][m.getValue().c].remove(m.getKey());
			
			nr = m.getValue().r + dr[idx];
			nc = m.getValue().c + dc[idx];
			
			if(jongsu.r == nr && jongsu.c == nc) flag = false;

			num[nr][nc].add(m.getKey());
			
			m.getValue().r = nr;
			m.getValue().c = nc;
		});
			

		// 2개 이상의 아두이노가 있게 될 경우 아두이노 파괴
		for(int r = 0; r < num.length; r++) {
			for(int c = 0; c < num[0].length; c++) {
				if(num[r][c].size() > 1) {
					num[r][c].forEach(n -> hashMap.remove(n));
					num[r][c].clear();
				}
			}
		}
		
		return flag;
	}

	// 종수 위치랑 아두이노 위치 거리
	private static int calDis(int nr, int nc) {
		return Math.abs(jongsu.r - nr) + Math.abs(jongsu.c - nc);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static boolean move(int d) {
		
		jongsu.r += dr[d];
		jongsu.c += dc[d];
		if(num[jongsu.r][jongsu.c].size() != 0) return false;

		return true;
	}
}
