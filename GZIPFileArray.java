import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPFileArray extends FileArray {

    public GZIPFileArray(String filePath, int n) throws IOException {
        super(filePath, n);
    }

    protected int[] read() throws IOException {
        File file = getFile();
        return readAndClose(new DataInputStream(new GZIPInputStream(new FileInputStream(file))));
    }

    protected void write(int[] values) throws IOException {
        File file = getFile();
        writeAndClose(new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file))), values);
    }
}