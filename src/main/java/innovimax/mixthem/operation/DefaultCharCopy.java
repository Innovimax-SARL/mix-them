package innovimax.mixthem.operation;

import innovimax.mixthem.io.DefaultCharReader;
import innovimax.mixthem.io.DefaultCharWriter;
import innovimax.mixthem.io.IInputChar;
import innovimax.mixthem.io.IOutputChar;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;

/**
* <p>Copy all characters.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultCharCopy implements ICopy {

    private final static int CHAR_BUFFER_SIZE = 1024;
    
    public void processFile(InputResource input, OutputStream out) throws IOException {
        char[] buffer = new char[CHAR_BUFFER_SIZE];
        IInputChar reader = new DefaultCharReader(input);
        IOutputChar writer = new DefaultCharWriter(out);
        while (reader.hasCharacter()) {
            final int len = reader.nextCharacters(buffer, CHAR_BUFFER_SIZE);
            writer.writeCharacters(buffer, len);
        }
        reader.close();
        writer.close();
    }    
    
}