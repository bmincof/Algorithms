package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-08-01
public class Main {
    static int[] parent;

    // u의 부모를 반환
    static int find(int u) {
        if (parent[u] == u) {
            return u;
        }

        return parent[u] = find(parent[u]);
    }

    // 연결되어 있으면 false
    static boolean link(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) {
            return false;
        }

        parent[v] = u;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 노드 수, 간선 수
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 트리를 만들기 위한 연산 횟수
        int operation = 0;

        // 연결된 상태에서 제거해야하는 간선을 찾기
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 이미 연결되어 있다면 간선을 하나 제거해야 함
            if (!link(u, v)) {
                operation++;
            }
        }

        // 추가로 연결해야하는 간선 찾기
        // parent가 다르다면 서로 다른 연결 요소이므로 두 요소를 연결해야 함
        Set<Integer> connectedComp = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            connectedComp.add(find(i));
        }

        operation += connectedComp.size() - 1;
        System.out.println(operation);
    }
}
