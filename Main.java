import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        int n = new Random().nextInt((1 << 5) - 1) + 1; // n ∼ U(1,…,2^5)

        try {
            FileArray fileArray = new FileArray(filePath, n);

            for (int i = 1; i < args.length; i++) {
                char command = args[i].charAt(0);
                switch (command) {
                    case 'i':
                        fileArray.incrementAll();
                        break;
                    case 'p':
                        fileArray.printArrayFormatted();
                        break;
                    default:
                        System.err.println("Comando non riconosciuto: " + command);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }
}