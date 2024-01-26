package algo;

import java.util.*;
import java.io.*;
/*
 * 카드 정렬하기
 */
public class BOJ1715 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long res = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++) {
        	pq.add(Long.parseLong(br.readLine()));
        }
        long cur = 0;
        while(pq.size()>=2) {
        	cur = pq.poll() + pq.poll();
        	res+=cur;
        	pq.add(cur);
        }
        System.out.println(res);
    }
}