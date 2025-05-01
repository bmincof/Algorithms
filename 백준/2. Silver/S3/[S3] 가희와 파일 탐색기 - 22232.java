import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-20
public class BOJ {
    // OS에서 인식하는 파일 확장자 목록
    static Set<String> os = new HashSet<>();
    static class File implements Comparable<File> {
        String file;
        String filename;
        String extension;

        File(String file) {
            this.file = file;
            String[] split = file.split("\\.");
            this.filename = split[0];
            this.extension = split[1];
        }

        @Override
        public int compareTo(File o) {
            // 파일명이 같다면
            if (this.filename.equals(o.filename)) {
                // 두 파일 중 OS에서 인식하는 확장자가 하나뿐이라면
                // OS에서 인식하는 파일을 먼저
                if (os.contains(this.extension) ^ os.contains(o.extension)) {
                    return os.contains(this.extension) ? -1 : 1;
                }
                return this.extension.compareTo(o.extension);
            }
            return this.filename.compareTo(o.filename);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 파일 개수, OS에서 인식하는 파일 확장자 개수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<File> files = new ArrayList<>();

        while (N-- > 0) {
            files.add(new File(br.readLine()));
        }

        while (M-- > 0) {
            os.add(br.readLine());
        }

        Collections.sort(files);

        for (File file : files) {
            sb.append(file.file).append("\n");
        }

        System.out.println(sb);
    }
}
