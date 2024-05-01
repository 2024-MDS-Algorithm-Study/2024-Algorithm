import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ11478 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                String pattern = s.substring(j, i + 1);
                set.add(pattern);
            }
        }

        System.out.println(set.size());
    }
}
