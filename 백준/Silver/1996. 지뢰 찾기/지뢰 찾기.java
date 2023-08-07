import java.io.BufferedReader;
import java.io.InputStreamReader;

// author : bmincof
// date   : 2023-08-07
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dr = {0, 0, 1, -1, 1, -1, -1, 1};
        int[] dc = {1, -1, 0, 0, 1, 1, -1, -1};

        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // i, j가 지뢰라면
                if (map[i][j] != '.') {
                    sb.append("*");
                } else {
                    int count = 0;
                    // i, j가 지뢰가 아니라면 주변의 지뢰 개수 세기
                    for (int dir = 0; dir < 8; dir++) {
                        int nr = i + dr[dir];
                        int nc = j + dc[dir];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                            continue;
                        }
                        if (map[nr][nc] != '.') {
                            count += map[nr][nc] - '0';
                        }
                    }
                    sb.append(count < 10 ? count : "M");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}