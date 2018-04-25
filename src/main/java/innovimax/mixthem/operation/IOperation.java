package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;

public interface IOperation {
  void processFiles(InputResource input1, InputResource input2, OutputStream out) throws MixException, IOException;
}
