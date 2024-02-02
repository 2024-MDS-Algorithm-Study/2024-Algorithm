import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
가운데를 말해요
 */
public class BOJ1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> max = new PriorityQueue<Integer>((o1, o2) -> Integer.compare(o2, o1));
        PriorityQueue<Integer> min = new PriorityQueue<Integer>((o1, o2) -> Integer.compare(o1, o2));

        for(int i = 1; i < N + 1; i++) {
        	int num = Integer.parseInt(br.readLine());
        	
        	max.add(num);
        	
        	if(max.size() > (i + 1)/2) min.add(max.poll());
        	if(!min.isEmpty() && max.peek() > min.peek()) {
        		min.add(max.poll());
        		max.add(min.poll());
        	}
        	sb.append(max.peek()+ "\n");
        }
        
        System.out.print(sb);
    }
}
