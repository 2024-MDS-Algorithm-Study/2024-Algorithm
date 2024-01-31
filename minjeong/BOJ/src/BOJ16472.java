import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int start = 0, end = 0, res = 0;
        Map<Character, Integer> m = new HashMap<>();
        m.put(s.charAt(end), 1);
        while (start <= end && end < s.length()) {
            if (m.size() <= n) {
                res = Math.max(res, end - start + 1);
                end++;
                if (end < s.length()) {
                    m.putIfAbsent(s.charAt(end), 0);
                    m.put(s.charAt(end), m.get(s.charAt(end)) + 1);
                }
            }
            else {
                m.put(s.charAt(start), m.get(s.charAt(start)) - 1);
                if (m.get(s.charAt(start)) == 0) m.remove(s.charAt(start));
                start++;
            }
        }
        System.out.println(res);
    }
}
