import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-08-06
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] sum = new int[N][M];

        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    sum[i][j] += Integer.parseInt(st.nextToken());

                    if (k == 1) {
                        sb.append(sum[i][j]).append(" ");
                    }
                }
                if (k == 1) {
                    sb.append("\n");
                }
            }
        }

        System.out.print(sb);
    }
}