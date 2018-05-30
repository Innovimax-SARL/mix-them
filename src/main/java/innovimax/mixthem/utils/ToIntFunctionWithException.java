package innovimax.mixthem.utils;

@FunctionalInterface
public interface ToIntFunctionWithException<T, E extends Exception> {
	int applyAsInt(T t) throws E;
}