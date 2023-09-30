import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author   : bmincof
// date     : 2023-09-30
public class Main {    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 0L && b == 0L && c == 0L) {
                break;
            }

            if (isRight(a, b, c)) {
                answer.append("right").append("\n");
            } else {
                answer.append("wrong").append("\n");
            }
        }

        System.out.println(answer);
    }

    static boolean isRight(long a, long b, long c) {
        a = a * a;
        b = b * b;
        c = c * c;

        return a + b == c || b + c == a || c + a == b;
    }
}