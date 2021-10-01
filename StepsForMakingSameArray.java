package beginner;

import java.io.*;
 
public class StepsForMakingSameArray {
	public static PrintWriter w = new PrintWriter(System.out);
	public static Reader in = new Reader();
	public static void main(String args[] ) throws Exception {
		int t = 1;//in.nextInt();
		while(t-- > 0) {
			w.println(solve());
		}
		w.flush();
		return;
	}
	public static int solve() throws Exception {
		int n = in.nextInt();
		int[] a = in.nextIntArray(n);
		int[] b = in.nextIntArray(n);
		int min = a[0];
		for (int i=1; i<n; i++) {
			if(a[i] < min)
				min = a[i];
		}
		boolean r = true;
		int ans = 0;
		for (int x = min; x>=0; x--) {
			r = true;
			ans = 0;
			for (int i=0; i<n; i++) {
				if(b[i] == 0 || (a[i]-x)%b[i] == 0) {
					if(b[i] == 0 && a[i] == x)
						continue;
					if(b[i] == 0 && a[i] != x) {
						ans = 0;
						r = false;
						break;
					}
					ans += (a[i]-x)/b[i];
				}
				else {
					ans = 0;
					r = false;
					break;
				}
			}
			if(r) {
				break;
			}
		}
		return (r ? ans : -1);
	}
}
 
class Reader {
	final private int BUFFER_SIZE = 1 << 16;
	private DataInputStream din;
	private byte[] buffer;
	private int bufferPointer, bytesRead;
 
	public Reader() {
		din = new DataInputStream(System.in);
		buffer = new byte[BUFFER_SIZE];
		bufferPointer = bytesRead = 0;
	}
	public String nextLine() throws IOException {
		int c = read();
		while(isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while(!isEndOfLine(c));
		return res.toString();
	}
	public String next() throws IOException {
		int c = read();
		while(isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while(!isSpaceChar(c));
		return res.toString();
	}
	public int nextInt() throws IOException {
		int ret = 0;
		byte c = read();
		while(c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if(neg)
			c = read();
		do {
			ret = ret * 10 + c - '0';
		} while((c = read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}
	public int[] nextIntArray(int n) throws IOException {
		int a[] = new int[n];
		for(int i = 0; i < n; i++)
			a[i] = nextInt();
		return a;
	}
	public int[][] next2dIntArray(int n, int m) throws IOException {
		int a[][] = new int[n][m];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				a[i][j] = nextInt();
		return a;
	}
	public char nextChar() throws IOException {
		return next().charAt(0);
	}
	public long nextLong() throws IOException {
		long ret = 0;
		byte c = read();
		while(c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if(neg)
			c = read();
		do {
			ret = ret * 10 + c - '0';
		} while((c = read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}
	public double nextDouble() throws IOException {
		double ret = 0, div = 1;
		byte c = read();
		while(c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if(neg)
			c = read();
		do {
			ret = ret * 10 + c - '0';
		} while((c = read()) >= '0' && c <= '9');
		if (c == '.') {
			while((c = read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}
		if(neg)
			return -ret;
		return ret;
	}
	private void fillBuffer() throws IOException {
		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
		if(bytesRead == -1)
			buffer[0] = -1;
	}
	private byte read() throws IOException {
		if(bufferPointer == bytesRead)
			fillBuffer();
		return buffer[bufferPointer++];
	}
	public void close() throws IOException {
		if(din == null)
			return;
		din.close();
	}
	public boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}
	private boolean isEndOfLine(int c) {
		return c == '\n' || c == '\r' || c == -1;
	}
}	
