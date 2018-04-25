package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

public interface IOperation {
  void processFiles(File file1, File file2, OutputStream out) throws MixException, IOException;
  void processFiles(InputStream input1, InputStream input2, OutputStream out) throws MixException, IOException;
}
