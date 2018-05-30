package innovimax.mixthem.utils;

@FunctionalInterface
public interface PredicateWithException<T, E extends Exception> {
	boolean test(T t) throws E;
}