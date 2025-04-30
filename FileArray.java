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
                output.writeInt(random.nextInt(1024));
            }
        }
    }

    public void print() throws IOException {
        try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            int numElements = 0;
            int maxValue = 0;

            while (input.available() > 0) {
                int value = input.readInt();
                maxValue = Math.max(maxValue, value);
                numElements++;
            }

            int space = Math.max(2, String.valueOf(maxValue).length());
            int index = String.valueOf(numElements - 1).length();

            try (DataInputStream input2 = new DataInputStream(new FileInputStream(file))) {
                for (int i = 0; i < numElements; i++) {
                    if (i % 5 == 0) {
                        if (i > 0) {
                            System.out.println();
                        }
                        System.out.printf("[%0" + index + "d-%0" + index + "d] ", i, Math.min(i + 4, numElements - 1));
                    }
                    System.out.printf("%" + space + "d ", input2.readInt());
                }
            }
            System.out.println();
        }
    }

    public void incrementAll() throws IOException {
        int[] values;
        try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            int numElements = (int) (file.length() / 4);
            values = new int[numElements];
            for (int i = 0; i < numElements; i++) {
                values[i] = input.readInt();
            }
        }

        for (int i = 0; i < values.length; i++) {
            values[i]++;
        }

        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file))) {
            for (int value : values) {
                output.writeInt(value);
            }
        }
    }
}