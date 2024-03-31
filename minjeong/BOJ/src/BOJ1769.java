import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1769 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int cnt = 0;
        while (s.length() > 1) {
            long tmp = 0;
            for (int i = 0; i < s.length(); i++) tmp += (s.charAt(i) - '0');
            s = String.valueOf(tmp);
            cnt++;
        }
        System.out.println(cnt);
        String res = Integer.parseInt(s) % 3 == 0 ? "YES" : "NO";
        System.out.println(res);
    }
}
