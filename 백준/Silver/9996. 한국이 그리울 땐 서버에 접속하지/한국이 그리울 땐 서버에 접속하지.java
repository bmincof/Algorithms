import java.io.BufferedReader;
import java.io.InputStreamReader;

// author   : bmincof
// date     : 2023-08-16
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        int starIdx = -1;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '*') {
                starIdx = i;
                break;
            }
        }

        char[] prefix = pattern.substring(0, starIdx).toCharArray();
        char[] suffix = pattern.substring(starIdx + 1).toCharArray();

        while (N-- > 0) {
            char[] file = br.readLine().toCharArray();
            if (file.length < pattern.length() - 1) {
                sb.append("NE").append("\n");
                continue;
            }

            boolean isAnswer = true;
            for (int i = 0; i < prefix.length; i++) {
                if (file[i] != prefix[i]) {
                    isAnswer = false;
                    break;
                }
            }

            for (int i = 0; i < suffix.length; i++) {
                if (file[file.length - 1 - i] != suffix[suffix.length - 1 - i]) {
                    isAnswer = false;
                    break;
                }
            }

            sb.append(isAnswer ? "DA" : "NE").append("\n");
        }

        System.out.print(sb);
    }
}