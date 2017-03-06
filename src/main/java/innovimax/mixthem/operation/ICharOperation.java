
package innovimax.mixthem.operation;
import innovimax.mixthem.MixException;

public interface ICharOperation extends IOperation {
	String process(int c1, int c2) throws MixException;
}
