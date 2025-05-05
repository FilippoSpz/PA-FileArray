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

    protected File getFile() {
        return file;
    }

    private void createRandomArray(int n) throws IOException {
        int[] values = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt(1024);
        }
        write(values);
    }

    public void print() throws IOException {
        int[] values = read();
        int maxValue = 0;

        for (int value : values) {
            maxValue = Math.max(maxValue, value);
        }

        int space = Math.max(2, String.valueOf(maxValue).length());
        int index = String.valueOf(values.length - 1).length();

        for (int i = 0; i < values.length; i++) {
            if (i % 5 == 0) {
                if (i > 0) {
                    System.out.println();
                }
                System.out.printf("[%0" + index + "d-%0" + index + "d] ", i, Math.min(i + 4, values.length - 1));
            }
            System.out.printf("%" + space + "d ", values[i]);
        }
        System.out.println();
    }

    public void incrementAll() throws IOException {
        int[] values = read();
        for (int i = 0; i < values.length; i++) {
            values[i]++;
        }
        write(values);
    }

    protected int[] readAndClose(DataInputStream input) throws IOException {
        int numElements = (int) (file.length() / 4);
        int[] values = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            values[i] = input.readInt();
        }
        return values;
    }

    protected void writeAndClose(DataOutputStream output, int[] values) throws IOException {
        for (int value : values) {
            output.writeInt(value);
        }
        output.close();
    }

    protected int[] read() throws IOException {
        return readAndClose(new DataInputStream(new FileInputStream(file)));
    }

    protected void write(int[] values) throws IOException {
        writeAndClose(new DataOutputStream(new FileOutputStream(file)), values);
    }
}