package innovimax.mixthem.io;

import java.io.BufferedInputStream;
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

  private static class FileResource extends InputResource {
    private final File file;
    private FileResource(final File file) {
      super();
      this.file = file;
    }
    @Override
    public BufferedReader newBufferedReader() throws IOException {
      return Files.newBufferedReader(this.file.toPath(), StandardCharsets.UTF_8);
    }
    @Override
    public BufferedInputStream newBufferedInputStream() throws IOException {
      return new BufferedInputStream(Files.newInputStream(this.file.toPath()));
    }
  }

  private static class InputStreamResource extends InputResource {
    private final InputStream input;
    private InputStreamResource(final InputStream input) {
      super();
      this.input = input;
    }
    @Override
    public BufferedReader newBufferedReader() {
      return new BufferedReader(new InputStreamReader(this.input, StandardCharsets.UTF_8));
    }
    @Override
    public BufferedInputStream newBufferedInputStream() {
      return new BufferedInputStream(this.input);
    }
  }
  
  /**
  * private Constructor
  */
  InputResource() {
    super();
  }
  
  public static InputResource createFile(final File file) {
    return new FileResource(file);
  }
  
  public static InputResource createInputStream(final InputStream input) {
    return new InputStreamResource(input);
  }
  
  /**
  * Returns a new BufferedReader for this input resource.
  * @return The BufferedReader for this input resource
  */
  public abstract BufferedReader newBufferedReader() throws IOException;
  
  /**
  * Returns a new BufferedInputStream for this input resource.
  * @return The BufferedInputStream for this input resource
  */
  public abstract BufferedInputStream newBufferedInputStream() throws IOException;
  
}
