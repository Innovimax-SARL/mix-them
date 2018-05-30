package innovimax.mixthem.utils;

@FunctionalInterface
public interface ConsumerWithException<T, E extends Exception> {
	void apply(T t) throws E;
}