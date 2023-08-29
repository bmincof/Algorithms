import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-08-29
public class Main {
    static class Word {
        String word;
        int dist;

        Word(String word) {
            this.word = word;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        // 자판간의 거리
        final int[][] dist = new int[26][26];

        final char[][] keyboard = {{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'}
                , {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', '\0'}
                , {'z', 'x', 'c', 'v', 'b', 'n', 'm', '\0', '\0', '\0'}};

        final int[] dr = {1, -1, 0, 0};
        final int[] dc = {0, 0, 1, -1};

        // 자판 간의 거리를 계산
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (keyboard[i][j] == '\0') {
                    continue;
                }

                int from = keyboard[i][j] - 'a';
                boolean[][] vis = new boolean[10][10];

                q.offer(new int[]{i, j});
                vis[i][j] = true;

                while (!q.isEmpty()) {
                    int[] pos = q.poll();
                    int to = keyboard[pos[0]][pos[1]] - 'a';

                    // 거리 계산하기
                    dist[from][to] = Math.abs(i - pos[0]) + Math.abs(j - pos[1]);

                    // 인접한 칸 집어넣기
                    for (int dir = 0; dir < 4; dir++) {
                        int nr = pos[0] + dr[dir];
                        int nc = pos[1] + dc[dir];

                        if (nr < 0 || nr >= 3 || nc < 0 || nc >= 10 || vis[nr][nc]) continue;

                        vis[nr][nc] = true;
                        if (keyboard[nr][nc] != '\0') {
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        int TC = Integer.parseInt(br.readLine());
        // 추천단어 거리 계산
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] input = st.nextToken().toCharArray();
            int recommend = Integer.parseInt(st.nextToken());

            Word[] words = new Word[recommend];
            // 거리 계산
            for (int j = 0; j < recommend; j++) {
                Word word = new Word(br.readLine());
                char[] recommended = word.word.toCharArray();

                for (int k = 0; k < input.length; k++) {
                    word.dist += dist[input[k] - 'a'][recommended[k] - 'a'];
                }

                words[j] = word;
            }

            Arrays.sort(words, (w1, w2) -> w1.dist != w2.dist ? w1.dist - w2.dist : w1.word.compareTo(w2.word));

            for (Word word : words) {
                answer.append(word.word).append(" ").append(word.dist).append("\n");
            }
        }

        System.out.println(answer);
    }
}