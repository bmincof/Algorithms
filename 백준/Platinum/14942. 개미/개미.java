import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-09-08
public class Main {
    static int[] distance;
    static int[][] sparse;
    static List<int[]>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int MAX_POW = 17;
        int N = Integer.parseInt(br.readLine());

        // 개미의 보유 에너지
        int[] energies = new int[N + 1];
        // 1번 방으로부터의 거리
        // 굴이 가장 길게 연결되어도 int 범위 내이다
        distance = new int[N + 1];
        // [i][j] -> [j]번 방에서 [2^i]단계 건너뛰었을 때 도착할 수 있는 방번호
        sparse = new int[MAX_POW][N + 1];
        // [i]번 방에서 갈 수 있는 다음 방
        tree = new LinkedList[N + 1];

        // 에너지 입력
        for (int i = 1; i <= N; i++) {
            energies[i] = Integer.parseInt(br.readLine());
            tree[i] = new LinkedList<>();
        }
        // 굴의 정보 입력
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            tree[from].add(new int[]{to, length});
            tree[to].add(new int[]{from, length});
        }

        // DFS로 1번 굴로부터의 거리를 계산
        dfs(1, 0, 0);

        // sparse 채우기
        for (int i = 1; i < MAX_POW; i++) {
            for (int j = 1; j <= N; j++) {
                sparse[i][j] = sparse[i - 1][sparse[i - 1][j]];
            }
        }

        // 갈 수 있는 방 중에서 최대한 1번 방과 가까운 방을 찾기
        int[] closest = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            // i번 개미의 에너지
            int energy = energies[i];
            // 개미가 있는 방 번호
            int room = i;
            for (int j = MAX_POW - 1; j >= 0; j--) {
                // room에서 2^j칸 뛰면 도착할 수 있는 방
                int jumpTo = sparse[j][room];
                // 굴 밖이 아니고 현재 남은 에너지로 갈 수 있는 방이면
                if (jumpTo != 0 && distance[room] - distance[jumpTo] <= energy) {
                    energy -= distance[room] - distance[jumpTo];
                    room = jumpTo;
                }
            }
            closest[i] = room;
        }

        for (int i = 1; i <= N; i++) {
            answer.append(closest[i]).append("\n");
        }
        System.out.print(answer);
    }

    static void dfs(int node, int length, int prev) {
        distance[node] += length;

        for (int[] next : tree[node]) {
            if (next[0] == prev) {
                continue;
            }

            sparse[0][next[0]] = node;
            dfs(next[0], distance[node] + next[1], node);
        }
    }
}