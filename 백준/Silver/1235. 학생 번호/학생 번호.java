import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// author : bmincof
// date   : 2023-08-08
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] numbers = new String[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = br.readLine();
        }

        int len = numbers[0].length();
        for (int i = 1; i <= len; i++) {
            Set<String> dupCheck = new HashSet<>();

            boolean canReduce = true;
            for (int j = 0; j < N; j++) {
                if (!dupCheck.add(numbers[j].substring(len - i))) {
                    canReduce = false;
                    break;
                }
            }

            if (canReduce) {
                System.out.println(i);
                break;
            }
        }
    }
}