package innovimax.mixthem.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
* <p>This is the representation of an input resource.</p>
* @author Innovimax
* @version 1.0
*/
public abstract class InputResource {

  private static class InputFileResource extends InputResource {
    private final File file;
    private InputFileResource(File file) {
      this.file = file;
    }
    @Override
    public BufferedReader newBufferedReader() throws IOException {
      return Files.newBufferedReader(input.toPath(), StandardCharsets.UTF_8);  
    }
  }

  private static class InputStreamResource extends InputResource {
    private final InputStream input;
    private InputStreamResource(File input) {
      this.input = input;
    }
    @Override
    public BufferedReader newBufferedReader() throws IOException {
      return new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
    }
  }
  
  /**
  * private Constructor
  */  
  public InputResource() {}
  
  public static InputResource createInputFile(File file) {
    return new InputFileResource(file);
  }
  
  public static InputResource createInputStream(InputStream input) {
    return new InputStreamResource(input);
  }
  
  /**
  * Returns a new BufferedReader for this input resource.
  * @return The BufferedReader for this input resource
  */
  public abstract BufferedReader newBufferedReader() throws IOException;
  
}
