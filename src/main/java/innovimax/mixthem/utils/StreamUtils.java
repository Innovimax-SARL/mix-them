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
    		Consumer<T> consume(final ConsumerWithException<T, E> ce) {
        		return arg -> {
            			try {
                			ce.accept(arg);
						} catch (final Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <E extends Exception>
    		IntConsumer consumeInt(final IntConsumerWithException<E> ice) {
        		return arg -> {
            			try {
                			ice.accept(arg);
						} catch (final Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <T, E extends Exception>
    		Predicate<T> test(final PredicateWithException<T, E> pe) {
        		return t -> {
            			try {
                			return pe.test(t);
						} catch (final Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <T, R, E extends Exception>
    		Function<T, R> apply(final FunctionWithException<T, R, E> fe) {
        		return t -> {
            			try {
                			return fe.apply(t);
						} catch (final Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <T, E extends Exception>
    		ToIntFunction<T> applyAsInt(final ToIntFunctionWithException<T, E> tife) {
        		return t -> {
            			try {
                			return tife.applyAsInt(t);
						} catch (final Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public <R, E extends Exception>
    		IntFunction<R> applyToInt(final IntFunctionWithException<R, E> ife) {
        		return arg -> {
            			try {
                			return ife.apply(arg);
						} catch (final Exception e) {
                			throw new RuntimeException(e);
            			}
        		};
	} 

	static public Collector<Byte, ?, ByteArrayOutputStream> byteCollector() {
		return Collector.of(
			ByteArrayOutputStream::new,
				ByteArrayOutputStream::write,
			(baos1, baos2) -> { 
				baos1.write(baos2.toByteArray(), 0, baos2.size());
				return baos1;
			}
		);		 
	}
}
