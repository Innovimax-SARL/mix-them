package innovimax.mixthem.operation;
import innovimax.mixthem.MixException;

public interface ILineOperation extends IOperation {
	String process(String line1, String line2) throws MixException;
}
