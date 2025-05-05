import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPFileArray extends FileArray {

    public GZIPFileArray(String filePath, int n) throws IOException {
        super(filePath, n);
    }

    protected int[] read() throws IOException {
        File file = getFile();
        DataInputStream input = new DataInputStream(new GZIPInputStream(new FileInputStream(file)));
        return readAndClose(input);
    }

    protected void write(int[] values) throws IOException {
        File file = getFile();
        DataOutputStream output = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
        writeAndClose(output, values);
    }
}