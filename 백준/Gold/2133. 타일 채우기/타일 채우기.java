import java.io.BufferedReader;
import java.io.InputStreamReader;

// author   : bmincof
// date     : 2023-08-20
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 3 * i 크기를 채울 수 있는 경우의 수
        int[] dp = new int[31];

        dp[0] = 1;
        dp[2] = 3;

        // i가 홀수일 때는 만들 수 없음
        for (int i = 4; i <= N; i += 2) {
            // 3 * (i - 2)개를 채워놓고 3 * 2를 만드는 경우
            dp[i] = dp[i - 2] * 3;
            // j = 4, 6, 8, 10, ... 일때마다 특별한 모양이 2개 나오므로 같이 계산해줌
            // 3 * (i - j) 개를 채워놓고 특별한 모양으로 3 * j개를 만드는 경우
            for (int j = i; j > 2; j -= 2) {
                dp[i] += dp[i - j] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}
