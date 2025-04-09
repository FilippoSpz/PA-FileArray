import java.io.*;
import java.util.Random;

public class FileArray {
    private final File file;

    public FileArray(String filePath, int n) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            createRandomArray(n);
        }
    }

    private void createRandomArray(int n) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                dos.writeInt(random.nextInt(1 << 10)); // Valori casuali tra 0 e 2^10
            }
        }
    }

    public void printArrayFormatted() throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            int numElements = 0;
            try (dis) {
                while (dis.available() > 0) {
                    dis.readInt(); // Legge un intero
                    numElements++; // Incrementa il conteggio
                }
            }
            int maxValue = 0;

            // Trova il valore massimo per calcolare la larghezza della colonna
            int[] values = new int[numElements];
            for (int i = 0; i < numElements; i++) {
                values[i] = dis.readInt();
                maxValue = Math.max(maxValue, values[i]);
            }

            // Calcola la larghezza della colonna
            int columnWidth = Math.max(2, String.valueOf(maxValue).length());
            int indexWidth = String.valueOf(numElements - 1).length();

            // Stampa i valori con formattazione
            for (int i = 0; i < numElements; i++) {
                if (i % 5 == 0) {
                    if (i > 0) System.out.println();
                    System.out.printf("[%0" + indexWidth + "d-%0" + indexWidth + "d] ", i, Math.min(i + 4, numElements - 1));
                }
                System.out.printf("%" + columnWidth + "d ", values[i]);
            }
            System.out.println();
        }
    }

    public void incrementAll() throws IOException {
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file));
             DataOutputStream dos = new DataOutputStream(new FileOutputStream(tempFile))) {
            while (dis.available() > 0) {
                dos.writeInt(dis.readInt() + 1);
            }
        }
        if (!file.delete() || !tempFile.renameTo(file)) {
            throw new IOException("Errore durante l'aggiornamento del file.");
        }
    }
}