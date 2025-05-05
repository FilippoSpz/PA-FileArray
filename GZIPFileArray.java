import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPFileArray extends FileArray {

    public GZIPFileArray(String filePath, int n) throws IOException {
        super(filePath, n);
    }

    protected int[] read() throws IOException {
        File file = getFile();
        try (DataInputStream input = new DataInputStream(new GZIPInputStream(new FileInputStream(file)))) {
            int numElements = (int) (file.length() / 4);
            int[] values = new int[numElements];
            for (int i = 0; i < numElements; i++) {
                values[i] = input.readInt();
            }
            return values;
        }
    }

    protected void write(int[] values) throws IOException {
        File file = getFile();
        try (DataOutputStream output = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)))) {
            for (int value : values) {
                output.writeInt(value);
            }
        }
    }
}