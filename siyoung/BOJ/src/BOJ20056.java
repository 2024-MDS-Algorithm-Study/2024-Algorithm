package algo;
import java.util.*;
import java.io.*;
/*
 * 마법사 상어와 파이어볼
 */
public class BOJ20056 {
	static class fire{
		int w, s, d;
		public fire(int w, int s, int d) {
			this.w = w;
			this.s = s; 
			this.d = d;
		}
	}
	static int[][] dd = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static int n, m, k;
	static LinkedList<fire>[][] map;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new LinkedList[n+1][n+1];
        for(int i=1; i<=n; i++) {
        	for(int j=1; j<=n; j++){
        		map[i][j] = new LinkedList<>();
        	}
        }
        for(int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	map[r][c].add(new fire(w, s, d));
        }
        
        for(int cnt=0; cnt<k; cnt++) {
        	LinkedList<fire>[][] tmp = new LinkedList[n+1][n+1];
        	//임시 배열 만들기
            for(int i=1; i<=n; i++) {
            	for(int j=1; j<=n; j++){
            		tmp[i][j] = new LinkedList<>();
            	}
            }
            //파이어볼 이동
        	for(int i=1; i<=n; i++) {
        		for(int j=1; j<=n; j++) {
    				while(map[i][j].size()>0) {
    					fire f = map[i][j].poll();
    					int di = i+dd[f.d][0]*f.s;
    					int dj = j+dd[f.d][1]*f.s;
    					if(di<=0) {
    						di = di%n+n;
    					}
    					if(di>n) {
    						di = di%n;
    						if(di==0) di=n;
    					}
    					if(dj<=0) {
    						dj = dj%n+n;
    					}
    					if(dj>n) {
    						dj = dj%n;
    						if(dj==0) dj=n;
    					}
    					if(di>0 && di<=n && dj>0 && dj<=n) {
    						tmp[di][dj].add(new fire(f.w, f.s, f.d));
    					}
        			}
        		}
        	}
        	// 파이어볼 병합
        	for(int i=1; i<=n; i++) {
        		for(int j=1; j<=n; j++) {
        			//방향 조건
        			boolean checkAllOdd = true;
        			boolean checkAllEven = true;
        			int wsum = 0;
        			int ssum = 0;
        			int size = tmp[i][j].size();
        			if(size<=1) continue;
    				while(tmp[i][j].size()>0) {
    					fire f = tmp[i][j].poll();
    					wsum += f.w;
    					ssum += f.s;
    					if(f.d%2!=0) checkAllEven = false;
    					else checkAllOdd = false;
        			}
    				//질량 0 이하면 사라짐
    				if(wsum/5<=0) continue;
    				if(checkAllOdd || checkAllEven) {
    					tmp[i][j].add(new fire(wsum/5, ssum/size, 0));
    					tmp[i][j].add(new fire(wsum/5, ssum/size, 2));
    					tmp[i][j].add(new fire(wsum/5, ssum/size, 4));
    					tmp[i][j].add(new fire(wsum/5, ssum/size, 6));
    				}
    				else {
    					tmp[i][j].add(new fire(wsum/5, ssum/size, 1));
    					tmp[i][j].add(new fire(wsum/5, ssum/size, 3));
    					tmp[i][j].add(new fire(wsum/5, ssum/size, 5));
    					tmp[i][j].add(new fire(wsum/5, ssum/size, 7));
    				}
        		}
        	}
        	map = tmp;
        }
        int max = 0;
        for(int i=1; i<=n; i++) {
        	for(int j=1; j<=n; j++) {
        		while(map[i][j].size()>0) {
					fire f = map[i][j].poll();
					max += f.w;
    			}
        	}
        }
        System.out.println(max);
    }
}