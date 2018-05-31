package innovimax.mixthem.utils;

import java.io.ByteArrayOutputStream;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;

public class StreamUtils {

	static public <T, E extends Exception>
    		Consumer<T> consume(ConsumerWithException<T, E> ce) {
        		return arg -> {
            			try {
                			ce.accept(arg);
            			} catch (Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <E extends Exception>
    		IntConsumer consumeInt(IntConsumerWithException<E> ice) {
        		return arg -> {
            			try {
                			ice.accept(arg);
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

	static public <R, E extends Exception>
    		IntFunction<R> applyToInt(IntFunctionWithException<R, E> ife) {
        		return arg -> {
            			try {
                			return ife.apply(arg);
            			} catch (Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public Collector<Byte, ?, ByteArrayOutputStream> byteCollector() {
		return Collector.of(
			ByteArrayOutputStream::new, 
			(baos, b) -> baos.write(b.byteValue()), 
			(baos1, baos2) -> { 
				baos1.write(baos2.toByteArray(), 0, baos2.size());
				return baos1;
			}
		);		 
	}
}
