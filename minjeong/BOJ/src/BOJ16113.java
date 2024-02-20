import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ16113 {
    static String[] nums = {"####.##.##.####", "#.#.#.#.#.", "###..#####..###", "###..####..####", "#.##.####..#..#",
            "####..###..####", "####..####.####", "###..#..#..#..#", "####.#####.####", "####.####..####"};
    static char[][] signal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int len = n / 5;
        signal = new char[5][len + 1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < len; j++) signal[i][j] = s.charAt(i * len + j);
            signal[i][len] = '.';
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            for (int idx = 0; idx < 10; idx++) {
                if (i > len - 2 && idx != 1) continue;
                String num = nums[idx];
                if (compareNum(num, i)) {
                    res.append(idx);
                    i += num.length() / 5 - 1;
                    break;
                }
            }
        }

        System.out.println(res);
    }

    static boolean compareNum(String num, int pos) {
        int len = num.length() / 5;
        for (int i = 0; i < num.length(); i++)
            if (num.charAt(i) != signal[i / len][pos + i % len]) return false;
        return true;
    }
}
