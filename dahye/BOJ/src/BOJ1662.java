import java.io.*;
import java.util.*;

/*
 * 압축
 */
public class BOJ1662 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		Stack<Integer> len = new Stack<Integer>();
		Stack<Integer> mul = new Stack<Integer>();
		
		int cnt = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if(c == '(') {
				cnt -= 1;
				int tmp = s.charAt(i - 1) - '0';
				len.add(cnt);
				mul.add(tmp);
				cnt = 0;
			} else if(c == ')') {
				int tmp = mul.pop();
				
				tmp *= cnt;
				
				cnt = len.pop() + tmp;
			}
			else cnt++;
		}
		System.out.println(cnt);
	}
}
