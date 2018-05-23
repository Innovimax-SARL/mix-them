package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface IOperation {
  void processFiles(List<InputResource> inputs, OutputStream output) throws MixException, IOException;
}
