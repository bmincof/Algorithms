import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// author   : bmincof
// date     : 2023-09-26
public class Main {
    static List<Integer>[] tree;
    static long[] value;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        tree = new LinkedList[N];
        value = new long[N];
        boolean[] isRoot = new boolean[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new LinkedList<>();
            isRoot[i] = true;
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[p].add(c);
            isRoot[c] = false;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            value[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if (isRoot[i]) {
                System.out.println(dfs(i, 0));
                return;
            }
        }
    }

    // i번 노드를 방문하면 하위 노드를 갔을 때와 가지 않았을 때 중 더 큰 값 선택
    // i번 노드를 방문하지 않으면 아래의 노드는 방문할 수 없음
    static long dfs(int curr, int depth) {
        // curr 노드를 이용해서 만들 수 있는 최댓값
        long maxValue = value[curr];

        for (int child : tree[curr]) {
            long val = dfs(child, depth + 1);
            maxValue += val;
        }

        // 자신을 포함하지 않거나 포함하거나 중에 더 큰 결과 반환
        if (depth == 0) {
            return maxValue;
        } else {
            return Math.max(maxValue, 0L);
        }
    }
}
