package innovimax.mixthem.io;

import java.io.InputStream;
import java.io.Reader;

/**
* This is the implementation of IToken interface.
* @see IToken
* @author Innovimax
* @version 1.0
*/
public abstract class Token implements IToken {
	
	private static class ByteToken extends Token {
		private final byte b;
		private ByteToken(final byte b) {
			this.b = b;
		}
		@Override
		public boolean isEmpty() {
			return this.b == -1;
		}
		@Override
		public byte asByte() {
			return this.b;
		}
		@Override
		public int asCharacter() {
			throw new UnsupportedOperationException("ByteToken does not have a character representation");
		}
		@Override
		public String asString() {
			throw new UnsupportedOperationException("ByteToken does not have a String representation");
		}
		@Override
		public InputStream asInputStream() {
			throw new UnsupportedOperationException("ByteToken does not have a InputStream representation");
		}
		@Override
		public Reader asReader() {
			throw new UnsupportedOperationException("ByteToken does not have a Reader representation");
		}
	}
	
	private static class CharToken extends Token {
		private final int c;
		private CharToken(final int c) {
			this.c = c;
		}
		@Override
		public boolean isEmpty() {
			return this.c == -1;
		}
		@Override
		public byte asByte() {
			throw new UnsupportedOperationException("CharToken does not have a byte representation");
		}
		@Override
		public int asCharacter() {
			return this.c;
		}
		@Override
		public String asString() {
			throw new UnsupportedOperationException("CharToken does not have a String representation");
		}
		@Override
		public InputStream asInputStream() {
			throw new UnsupportedOperationException("CharToken does not have a InputStream representation");
		}
		@Override
		public Reader asReader() {
			throw new UnsupportedOperationException("CharToken does not have a Reader representation");
		}
	}

	private static class LineToken extends Token {
		private final String line;
		private LineToken(final String line) {
			this.line = line;
		}
		@Override
		public boolean isEmpty() {
			return this.line == null;
		}
		@Override
		public byte asByte() {
			throw new UnsupportedOperationException("LineToken does not have a byte representation");
		}
		@Override
		public int asCharacter() {
			throw new UnsupportedOperationException("LineToken does not have a character representation");
		}
		@Override
		public String asString() {
			return this.line;
		}
		@Override
		public InputStream asInputStream() {
			throw new UnsupportedOperationException("LineToken does not have a InputStream representation");
		}
		@Override
		public Reader asReader() {
			throw new UnsupportedOperationException("LineToken does not have a Reader representation");
		}
	}

	private static class FileByteToken extends Token {
		private final InputStream file;
		private FileByteToken(final InputStream file) {
			this.file = file;
		}
		@Override
		public boolean isEmpty() {
			return this.file == null;
		}
		@Override
		public byte asByte() {
			throw new UnsupportedOperationException("FileByteToken does not have a byte representation");
		}
		@Override
		public int asCharacter() {
			throw new UnsupportedOperationException("FileByteToken does not have a character representation");
		}
		@Override
		public String asString() {
			throw new UnsupportedOperationException("FileByteToken does not have a String representation");
		}
		@Override
		public InputStream asInputStream() {
			return this.file;
		}
		@Override
		public Reader asReader() {
			throw new UnsupportedOperationException("FileByteToken does not have a Reader representation");
		}
	}

	private static class FileCharToken extends Token {
		private final Reader file;
		private FileCharToken(final Reader file) {
			this.file = file;
		}
		@Override
		public boolean isEmpty() {
			return this.file == null;
		}
		@Override
		public byte asByte() {
			throw new UnsupportedOperationException("FileCharToken does not have a byte representation");
		}
		@Override
		public int asCharacter() {
			throw new UnsupportedOperationException("FileCharToken does not have a character representation");
		}
		@Override
		public String asString() {
			throw new UnsupportedOperationException("FileCharToken does not have a String representation");
		}
		@Override
		public InputStream asInputStream() {
			throw new UnsupportedOperationException("FileCharToken does not have a InputStream representation");
		}
		@Override
		public Reader asReader() {
			return this.file;
		}
	}

	/**
	* private Constructor
	*/ 	
	public Token() {}
	
	public static IToken createByteToken(final byte b) {
		return new ByteToken(b);
	}
	
	public static IToken createCharToken(final int c) {
		return new CharToken(c);
	}
	
	public static IToken createLineToken(final String line) {
		return new LineToken(line);
	}
	
	public static IToken createFileByteToken(final InputStream file) {
		return new FileByteToken(file);
	}

	public static IToken createFileCharToken(final Reader file) {
		return new FileCharToken(file);
	}

}
