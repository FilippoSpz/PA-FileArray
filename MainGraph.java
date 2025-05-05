import java.io.File;

public class MainGraph {
    public static void main(String[] args) {
        for (int n = 0; n <= 100; n++) {

            long gzipFileLength = new File("testgraph.zip").length();
            long binFileLength = new File("testgraph.bin").length();

            for (int i = 0; i < 30; i++) {
                System.out.printf("%3d ; %5d ; %5d%n", n, gzipFileLength, binFileLength);
            }

            new File("testgraph.bin").deleteOnExit();
            new File("testgraph.zip").deleteOnExit();

        }
    }
}