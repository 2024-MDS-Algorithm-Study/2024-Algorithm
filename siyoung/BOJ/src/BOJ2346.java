package algo;

import java.util.*;
import java.io.*;
/*
 * 풍선 터뜨리기
 */
public class BOJ2346 {
	static class node{
		int move, idx;
		public node(int move, int idx) {
			this.move = move;
			this.idx = idx;
		}
	}
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<node> arr = new ArrayDeque<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
        	arr.add(new node(Integer.parseInt(st.nextToken()), i+1));
        }
//        System.out.println(arr);
        StringBuilder res = new StringBuilder();
        for(int i=0; i<n; i++) {
        	node cn = arr.pollFirst();
//        	System.out.println("cn: "+cn.idx);
        	res.append(cn.idx+" ");
        	int cnt = cn.move;
        	if(cnt>0) cnt--;
        	while(cnt!=0 && !arr.isEmpty()) {
//        		System.out.println(arr.peekFirst().idx);
        		if(cnt>0) {
        			arr.addLast(arr.pollFirst());
        			cnt--;
        		}
        		else if(cnt<0){
        			arr.addFirst(arr.pollLast());
        			cnt++;
        		}
        	}
        }
        System.out.println(res);
    }
}