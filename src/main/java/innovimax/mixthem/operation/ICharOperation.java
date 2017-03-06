
package innovimax.mixthem.operation;
import innovimax.mixthem.MixException;

public interface ICharOperation extends IOperation {
	int[] process(int c1, int c2) throws MixException;
}
