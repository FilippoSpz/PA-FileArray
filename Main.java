import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        int n = new Random().nextInt(32) + 1; // Random size between 1 and 32

        try {
            FileArray fileArray;

            if (filePath.endsWith(".zip")) {
                fileArray = new GZIPFileArray(filePath, n);
            } else {
                fileArray = new FileArray(filePath, n);
            }

            for (int i = 1; i < args.length; i++) {
                switch (args[i].charAt(0)) {
                    case 'i' -> fileArray.incrementAll();
                    case 'p' -> fileArray.print();
                    default -> System.err.println("Unrecognized command: " + args[i]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}