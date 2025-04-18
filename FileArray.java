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
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file))) {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                output.writeInt(random.nextInt(1 << 10));
            }
        }
    }

    public void printArrayFormatted() throws IOException {
        try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            int numElements = 0;
            int maxValue = 0;

            while (input.available() > 0) {
                int value = input.readInt();
                maxValue = Math.max(maxValue, value);
                numElements++;
            }

            int column = Math.max(2, String.valueOf(maxValue).length());
            int index = String.valueOf(numElements - 1).length();

            try (DataInputStream input2 = new DataInputStream(new FileInputStream(file))) {
                for (int i = 0; i < numElements; i++) {
                    if (i % 5 == 0) {
                        if (i > 0) System.out.println();
                        System.out.printf("[%0" + index + "d-%0" + index + "d] ", i, Math.min(i + 4, numElements - 1));
                    }
                    System.out.printf("%" + column + "d ", input2.readInt());
                }
            }
            System.out.println();
        }
    }

    public void incrementAll() throws IOException {
        File temp = new File(file.getAbsolutePath() + ".tmp");
        try (DataInputStream input = new DataInputStream(new FileInputStream(file));
             DataOutputStream output = new DataOutputStream(new FileOutputStream(temp))) {
            while (input.available() > 0) {
                output.writeInt(input.readInt() + 1);
            }
        }
        if (!file.delete() || !temp.renameTo(file)) {
            throw new IOException("file error");
        }
    }
}