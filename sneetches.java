import java.io.*;
import java.util.*;
public class sneetches {
public static void main(String[] args) throws Exception {
	JoltyScanner input = new JoltyScanner(new BufferedInputStream(System.in));
	PrintWriter out = new PrintWriter(System.out);
	int T = input.nextInt();
	for(int t = 0; t<T; t++)
	{
		int n = input.nextInt(), m = input.nextInt();
		String s = input.next();
		IT it = new IT(n, s);
		for(int i = 0; i<m; i++)
		{
			int a = input.nextInt()-1, b = input.nextInt()-1;
			it.add(a, b);
			out.println(it.gets(0, n-1)+" "+it.getp(0,  n-1));
		}
	}
	
	
	out.close();
}
/*
 * Interval tree to support the following operations:
 *   Get the longest continuous string of S's or P's in a string consisting of only those 2 letters
 *   Change every character in a substring to the opposite character.
 */
static class IT
{
	int[] prep, suffp, pres, suffs, maxp, maxs, a, b, prop;
	String s;
	IT(int n, String ss)
	{
		prep = new int[4*n];
		suffp = new int[4*n];
		pres = new int[4*n];
		suffs = new int[4*n];
		maxs = new int[4*n];
		maxp = new int[4*n];
		prop = new int[4*n];
		a = new int[4*n];
		b = new int[4*n];
		s = ss;
		init(0,0, n-1);
	}
	void init(int at, int l, int r)
	{
		a[at] = l;
		b[at] = r;
		if(l==r)
		{
			if(s.charAt(l) == 'S')
			{
				maxs[at] = pres[at] = suffs[at] = 1;
			}
			else
			{
				maxp[at] = prep[at] = suffp[at] = 1;
			}
		}
		else 
		{
			int mid = (l+r)/2;
			int L = 2*at+1, R = 2*at+2;
			init(L,l,mid);
			init(R,mid+1,r);
			prep[at] = prep[L];
			if(prep[L] == b[L] - a[L] + 1) prep[at] += prep[R];
			pres[at] = pres[L];
			if(pres[L] == b[L] - a[L] + 1) pres[at] += pres[R];
			suffp[at] = suffp[R];
			if(suffp[R] == b[R] - a[R] + 1) suffp[at] += suffp[L];
			suffs[at] = suffs[R];
			if(suffs[R] == b[R] - a[R] + 1) suffs[at] += suffs[L];
			maxp[at] = Math.max(maxp[L], maxp[R]);
			maxp[at] = Math.max(maxp[at], suffp[L] + prep[R]);
			maxs[at] = Math.max(maxs[L], maxs[R]);
			maxs[at] = Math.max(maxs[at], suffs[L] + pres[R]);
		}
	}
	int getp(int x, int y)
	{
		return maxp[0];
	}
	int gets(int x, int y)
	{
		return maxs[0];
	}
	void add(int x, int y)
	{
		go3(x, y, 0);
	}
	void go3(int x, int y, int at)
	{
		if(y < a[at] || x > b[at]) return;
		x = Math.max(x, a[at]);
		y = Math.min(y, b[at]);
		if(y == b[at] && x == a[at])
		{
			int temp = maxp[at];
			maxp[at] = maxs[at];
			maxs[at] = temp;
			temp = prep[at];
			prep[at] = pres[at];
			pres[at] = temp;
			temp = suffp[at];
			suffp[at] = suffs[at];
			suffs[at] = temp;
			prop[at] ^= 1;
			return;
		}
		if(prop[at] != 0)
		{
			int L = 2*at + 1, R = 2*at + 2;
			go3(a[L], b[L], L);
			go3(a[R], b[R], R);
			prop[at] = 0;
		}
		int L = 2*at+1, R = 2*at+2;
		go3(x, y, L);
		go3(x, y, R);
		prep[at] = prep[L];
		if(prep[L] == b[L] - a[L] + 1) prep[at] += prep[R];
		pres[at] = pres[L];
		if(pres[L] == b[L] - a[L] + 1) pres[at] += pres[R];
		suffp[at] = suffp[R];
		if(suffp[R] == b[R] - a[R] + 1) suffp[at] += suffp[L];
		suffs[at] = suffs[R];
		if(suffs[R] == b[R] - a[R] + 1) suffs[at] += suffs[L];
		maxp[at] = Math.max(maxp[L], maxp[R]);
		maxp[at] = Math.max(maxp[at], suffp[L] + prep[R]);
		maxs[at] = Math.max(maxs[L], maxs[R]);
		maxs[at] = Math.max(maxs[at], suffs[L] + pres[R]);
	}
}
public static class JoltyScanner {
	public static final int BUFFER_SIZE = 1 << 16;
	public static final char NULL_CHAR = (char) -1;
    BufferedInputStream in;
	StringBuilder str = new StringBuilder();
	byte[] buffer = new byte[BUFFER_SIZE];
	boolean EOF_FLAG = false;
    char c = NULL_CHAR;
	int bufferIdx = 0;
    int size = 0;
	public JoltyScanner(InputStream in) {
		this.in = new BufferedInputStream(in, BUFFER_SIZE);
	}
	public int nextInt() {
		long x = nextLong();
		if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
			throw new ArithmeticException("Scanned value overflows integer");
		}
		return (int) x;
	}
	public long nextLong() {
		boolean negative = false;
		if (c == NULL_CHAR) {
			c = nextChar();
		}
		for (; !EOF_FLAG && (c < '0' || c > '9'); c = nextChar()) {
			if (c == '-') {
				negative = true;
			}				
		}
		checkEOF();
		long res = 0;
		for (; c >= '0' && c <= '9'; c = nextChar()) {
			res = (res << 3) + (res << 1) + c - '0';
		}
		return negative ? -res : res;
	}
	public String nextLine() {
		checkEOF();
		str.setLength(0);
		for (c = c == NULL_CHAR ? nextChar() : c; !EOF_FLAG && c != '\n'; c = nextChar()) {
			str.append(c);
		}
		c = NULL_CHAR;
		return str.toString();
	}
	public String next() {
		checkEOF();
		for (c = c == NULL_CHAR ? nextChar() : c; Character.isWhitespace(c); checkEOF()) {
			c = nextChar();
		}
		str.setLength(0);
		for (; !EOF_FLAG && !Character.isWhitespace(c); c = nextChar()) {
			str.append(c);
		}
		return str.toString();
	}
	public String nextOrNull() {
		try {
			return next();
		} catch (EndOfFileException e) {
			return null;
		}
	}
    public char peekChar() {
        if (c == NULL_CHAR) {
            c = nextChar();
        }
        return c;
    }
	public char nextChar() {
		if (EOF_FLAG) {
			return NULL_CHAR;
		}
		while (bufferIdx == size) {
			try {
				size = in.read(buffer);
				if (size == -1) {
					throw new Exception();
				}
			} catch (Exception e) {
				EOF_FLAG = true;
				return NULL_CHAR;
			}
			if (size == -1) {
				size = BUFFER_SIZE;
			}
			bufferIdx = 0;
		}
		return (char) buffer[bufferIdx++];
	}
	public void checkEOF() {
		if (EOF_FLAG) {
			throw new EndOfFileException();
		}
	}
	public class EndOfFileException extends RuntimeException {
	}
}
}