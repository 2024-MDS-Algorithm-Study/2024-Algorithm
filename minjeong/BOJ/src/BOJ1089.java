import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String[] nums = {"####.##.##.####", "..#..#..#..#..#", "###..#####..###", "###..####..####", "#.##.####..#..#",
            "####..###..####", "####..####.####", "###..#..#..#..#", "####.#####.####", "####.####..####"};
    static char[][] signal;
    static int n, len;
    static double[] sum, size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        len = 4 * n - 1;
        signal = new char[5][len];
        for (int i = 0; i < 5; i++) {
            signal[i] = br.readLine().toCharArray();
        }

        sum = new double[n];
        size = new double[n];
        double totalSum = 0, totalSize = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (compareNum(nums[j], i * 4)) {
                    sum[i] += j * Math.pow(10, n - i - 1);
                    size[i]++;
                }
            }
            totalSize *= size[i];
        }

        for (int i = 0; i < n; i++) {
            if (size[i] != 0) totalSum += totalSize / size[i] * sum[i];
        }

        if (totalSize != 0) System.out.println(totalSum / totalSize);
        else System.out.println(-1);
    }

    static boolean compareNum(String num, int pos) {
        for (int i = 0; i < 15; i++)
            if (num.charAt(i) == '.' && signal[i / 3][pos + i % 3] == '#') return false;
        return true;
    }
}
