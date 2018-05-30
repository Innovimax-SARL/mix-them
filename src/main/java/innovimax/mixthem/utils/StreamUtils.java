package innovimax.mixthem.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class StreamUtils {

	static public <T, E extends Exception>
    		Consumer<T> consume(ConsumerWithException<T, E> ce) {
        		return arg -> {
            			try {
                			ce.apply(arg);
            			} catch (Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <T, E extends Exception>
    		Predicate<T> test(PredicateWithException<T, E> pe) {
        		return arg -> {
            			try {
                			return pe.test(arg);
            			} catch (Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <T, R, E extends Exception>
    		Function<T, R> apply(FunctionWithException<T, R, E> fe) {
        		return arg -> {
            			try {
                			return fe.apply(arg);
            			} catch (Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <T, E extends Exception>
    		ToIntFunction<T> applyAsInt(ToIntFunctionWithException<T, E> tife) {
        		return arg -> {
            			try {
                			return tife.applyAsInt(arg);
            			} catch (Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

}
