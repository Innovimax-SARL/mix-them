package innovimax.mixthem.operation;
import innovimax.mixthem.MixException;

interface ILineOperation extends IOperation {
	public String process(String line1, String line2) throws MixException;
}
