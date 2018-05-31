package innovimax.mixthem.io;

import java.io.InputStream;
import java.io.Reader;

/**
* <p>This is the representation of an input token.</p>
* @author Innovimax
* @version 1.0
*/
public abstract class InputToken {
	
	private static class ByteToken extends InputToken {
		private final byte b;
		private ByteToken(final byte b) {
			this.b = b;
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
	
	private static class CharToken extends InputToken {
		private final int c;
		private CharToken(final int c) {
			this.c = c;
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

	private static class LineToken extends InputToken {
		private final String line;
		private LineToken(final String line) {
			this.line = line;
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

	private static class FileByteToken extends InputToken {
		private final InputStream file;
		private FileByteToken(final InputStream file) {
			this.file = file;
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

	private static class FileCharToken extends InputToken {
		private final Reader file;
		private FileCharToken(final Reader file) {
			this.file = file;
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
	public InputToken() {}
	
	public static InputToken createByteToken(final byte b) {
		return new ByteToken(b);
	}
	
	public static InputToken createCharToken(final int c) {
		return new CharToken(c);
	}
	
	public static InputToken createLineToken(final String line) {
		return new LineToken(line);
	}
	
	public static InputToken createFileByteToken(final InputStream file) {
		return new FileByteToken(file);
	}

	public static InputToken createFileCharToken(final Reader file) {
		return new FileCharToken(file);
	}

	/**
	* Returns the token value as a byte.
	* @return The token value as a byte
	*/
	public abstract byte asByte();

	/**
	* Returns the token value as a character.
	* @return The token value as a character
	*/
	public abstract int asCharacter();

	/**
	* Returns the token value as a string.
	* @return The token value as a string
	*/
	public abstract String asString();

	/**
	* Returns the parameter value as a file input stream.
	* @return The parameter value as a file input stream
	*/
	public abstract InputStream asInputStream();
	
	/**
	* Returns the parameter value as a file reader.
	* @return The parameter value as a file reader
	*/
	public abstract Reader asReader();
	
}
