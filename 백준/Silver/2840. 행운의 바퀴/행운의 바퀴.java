import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author   : bmincof
// date     : 2023-09-23
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] wheel = new char[N];
        int[] isDup = new int[26];

        int cursor = 0;
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());

            int offset = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            cursor = (cursor + offset) % N;
            if (isDup[c - 'A'] != 0 && isDup[c - 'A'] != cursor || wheel[cursor] != '\0' && wheel[cursor] != c) {
                System.out.println("!");
                return;
            }
            wheel[cursor] = c;
            isDup[c - 'A'] = cursor;
        }

        for (int i = 0; i < N; i++) {
            char value = wheel[(cursor + N - i) % N];
            System.out.print(value == '\0' ? "?" : value);
        }
    }
}