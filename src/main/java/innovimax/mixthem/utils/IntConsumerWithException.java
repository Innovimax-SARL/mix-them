package innovimax.mixthem.utils;

@FunctionalInterface
public interface IntConsumerWithException<E extends Exception> {
	void accept(int value) throws E;
}