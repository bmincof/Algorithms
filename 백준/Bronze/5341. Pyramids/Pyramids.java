import java.io.BufferedReader;
import java.io.InputStreamReader;

// author   : bmincof
// date     : 2023-10-01
public class Main {    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            int N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            answer.append(N * (N + 1) / 2).append("\n");
        }

        System.out.print(answer);
    }
}