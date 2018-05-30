package innovimax.mixthem.utils;

@FunctionalInterface
public interface IntFunctionWithException<R, E extends Exception> {
	R apply(int value) throws E;
}