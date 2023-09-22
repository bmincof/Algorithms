import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author   : bmincof
// date     : 2023-09-22
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] memo = new int[N];
        boolean[] alive = new boolean[N];

        for (int i = 0; i < N; i++) {
            memo[i] = Integer.parseInt(st.nextToken());
            alive[i] = true;
        }

        StringBuilder answer = new StringBuilder();
        int curr = 0;
        for (int i = 0; i < N; i++) {
            answer.append(curr + 1).append(" ");
            if (i == N - 1) {
                break;
            }

            alive[curr] = false;
            int value = memo[curr];

            if (value < 0) {
                while (value < 0) {
                    curr = (curr + N - 1) % N;
                    if (alive[curr]) {
                        value++;
                    }
                }
            } else {
                while (value > 0) {
                    curr = (curr + 1) % N;
                    if (alive[curr]) {
                        value--;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}