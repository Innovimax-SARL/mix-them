package innovimax.mixthem.operation;

import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;

public interface ICopy {
    void processFile(InputResource input, OutputStream out) throws IOException;
}
