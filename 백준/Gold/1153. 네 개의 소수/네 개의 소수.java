import java.io.BufferedReader;
import java.io.InputStreamReader;

// author   : bmincof
// date     : 2023-09-10
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isNotPrime = new boolean[1_000_010];

        isNotPrime[0] = isNotPrime[1] = true;
        for (int i = 2; i * i <= 1_000_000; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * 2; j <= 1_000_000; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());

        // 짝수면 2를 2개, 홀수면 2, 3을 사용해서 소수 2개만 찾으면 되도록 변경
        boolean isEven = N % 2 == 0;
        N = isEven ? (N - 4) : (N - 5);

        // 골드바흐의 추측을 이용 -> 짝수는 두 소수의 합으로 표현 가능
        int a = N / 2;
        int b = N / 2;
        while (a >= 2 && b < N) {
            if (!isNotPrime[a] && !isNotPrime[b]) {
                System.out.printf("2 %d %d %d", isEven ? 2 : 3, a, b);
                return;
            }
            a--;
            b++;
        }

        System.out.println(-1);
    }
}