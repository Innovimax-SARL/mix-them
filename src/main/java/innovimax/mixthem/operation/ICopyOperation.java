package innovimax.mixthem.operation;

import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;

public interface ICopyOperation extends IOperation {
    /**
 	* Processes file copy.
	* @param input Input resource to be copied
	* @param output Output result writer 	
 	* @throws IOException - If an i/o error occurs	
	*/	
    void process(InputResource input, OutputStream output) throws IOException;
}
