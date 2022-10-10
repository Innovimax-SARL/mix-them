package innovimax.mixthem.io;

import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;

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
            super();
            this.b = b;
        }
		@Override
		public String toString() {
			return Byte.toString(this.b);
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
		public byte[] asByteArray() {
			throw new UnsupportedOperationException("ByteToken does not have a byte array representation");
		}
		@Override
		public char[] asCharacterArray() {
			throw new UnsupportedOperationException("ByteToken does not have a character array representation");
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
            super();
            this.c = c;
        }
		@Override
		public String toString() {
			return Integer.toString(this.c);
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
		public byte[] asByteArray() {
			throw new UnsupportedOperationException("CharToken does not have a byte array representation");
		}
		@Override
		public char[] asCharacterArray() {
			throw new UnsupportedOperationException("CharToken does not have a character array representation");
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

	private static class ByteArrayToken extends Token {
		private final byte[] array;
		private final int len;
		private ByteArrayToken(final byte[] array, final int len) {
            super();
            this.array = array;
            this.len = len;
        }
		@Override
		public String toString() {
			return Arrays.toString(this.array);
		}
		@Override
		public boolean isEmpty() {
			return this.array == null;
		}
		@Override
		public byte asByte() {
			throw new UnsupportedOperationException("ByteArrayToken does not have a byte representation");
		}
		@Override
		public int asCharacter() {
			throw new UnsupportedOperationException("ByteArrayToken does not have a character representation");
		}
		@Override
		public byte[] asByteArray() {
			return Arrays.copyOfRange(this.array, 0, this.len);
		}
		@Override
		public char[] asCharacterArray() {
			throw new UnsupportedOperationException("ByteArrayToken does not have a character array representation");
		}
		@Override
		public String asString() {
			throw new UnsupportedOperationException("ByteArrayToken does not have a String representation");
		}
		@Override
		public InputStream asInputStream() {
			throw new UnsupportedOperationException("ByteArrayToken does not have a InputStream representation");
		}
		@Override
		public Reader asReader() {
			throw new UnsupportedOperationException("ByteArrayToken does not have a Reader representation");
		}
	}

	private static class CharArrayToken extends Token {
		private final char[] array;
		private final int len;
		private CharArrayToken(final char[] array, final int len) {
            super();
            this.array = array;
            this.len = len;
        }
		@Override
		public String toString() {
			return Arrays.toString(this.array);
		}
		@Override
		public boolean isEmpty() {
			return this.array == null;
		}
		@Override
		public byte asByte() {
			throw new UnsupportedOperationException("CharArrayToken does not have a byte representation");
		}
		@Override
		public int asCharacter() {
			throw new UnsupportedOperationException("CharArrayToken does not have a character representation");
		}
		@Override
		public byte[] asByteArray() {
			throw new UnsupportedOperationException("CharArrayToken does not have a byte array representation");
		}
		@Override
		public char[] asCharacterArray() {
			return Arrays.copyOfRange(this.array, 0, this.len);
		}
		@Override
		public String asString() {
			throw new UnsupportedOperationException("CharArrayToken does not have a String representation");
		}
		@Override
		public InputStream asInputStream() {
			throw new UnsupportedOperationException("CharArrayToken does not have a InputStream representation");
		}
		@Override
		public Reader asReader() {
			throw new UnsupportedOperationException("CharArrayToken does not have a Reader representation");
		}
	}

	private static class LineToken extends Token {
		private final String line;
		private LineToken(final String line) {
            super();
            this.line = line;
        }
		@Override
		public String toString() {
			return this.line;
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
		public byte[] asByteArray() {
			throw new UnsupportedOperationException("LineToken does not have a byte array representation");
		}
		@Override
		public char[] asCharacterArray() {
			throw new UnsupportedOperationException("LineToken does not have a character array representation");
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
            super();
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
		public byte[] asByteArray() {
			throw new UnsupportedOperationException("FileByteToken does not have a byte array representation");
		}
		@Override
		public char[] asCharacterArray() {
			throw new UnsupportedOperationException("FileByteToken does not have a character array representation");
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
			throw new UnsupportedOperationException("FileCharToken does not have a Reader representation");
		}
	}

	private static class FileCharToken extends Token {
		private final Reader file;
		private FileCharToken(final Reader file) {
            super();
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
		public byte[] asByteArray() {
			throw new UnsupportedOperationException("FileCharToken does not have a byte array representation");
		}
		@Override
		public char[] asCharacterArray() {
			throw new UnsupportedOperationException("FileCharToken does not have a character array representation");
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
    Token() {
        super();
    }
	
	public static IToken createByteToken(final byte b) {
		return new ByteToken(b);
	}
	
	public static IToken createCharToken(final int c) {
		return new CharToken(c);
	}

	public static IToken createByteArrayToken(final byte[] array, final int len) {
		return new ByteArrayToken(array, len);
	}
	
	public static IToken createCharArrayToken(final char[] array, final int len) {
		return new CharArrayToken(array, len);
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
