package innovimax.mixthem.operation;

import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;

public interface ICopyOperation {
    void process(InputResource input, OutputStream out) throws IOException;
}
