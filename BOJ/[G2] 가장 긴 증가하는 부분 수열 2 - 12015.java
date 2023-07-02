package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-07-02
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        // 길이 [i]인 LIS를 만들 수 있는 가장 작은 마지막 수
        List<Integer> lis = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(seq[0]);

        for(int i = 1; i < N; i++) {
            int next = seq[i];

            int idx = Collections.binarySearch(lis, next);
            idx = idx < 0 ? -idx-1 : idx;

            if(idx >= lis.size()) {
                lis.add(next);
            } else {
                lis.set(idx, next);
            }
        }

        System.out.println(lis.size());
    }
}
