package innovimax.mixthem.operation;

import innovimax.mixthem.io.DefaultByteReader;
import innovimax.mixthem.io.DefaultByteWriter;
import innovimax.mixthem.io.IByteInput;
import innovimax.mixthem.io.IByteOutput;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;

/**
* <p>Copy all bytes.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultByteCopy implements ICopy {

    private final static int BYTE_BUFFER_SIZE = 1024;
    
    public void processFile(InputResource input, OutputStream out) throws IOException {
        byte[] buffer = new byte[BYTE_BUFFER_SIZE];
        IByteInput reader = new DefaultByteReader(input);
        IByteOutput writer = new DefaultByteWriter(out);
        while (reader.hasByte()) {
            final int len = reader.nextBytes(buffer, BYTE_BUFFER_SIZE);
            writer.writeBytes(buffer, len);
        }
        reader.close();
        writer.close();
    }    
    
}
