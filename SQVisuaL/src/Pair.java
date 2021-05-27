
public class Pair<A, B> {
	private A f;
	private B s;
	public Pair(A f, B s) {
		this.f = f;
		this.s = s;
	}
	
	public A getFirst() {
		return this.f;
	}
	
	public B getSecond() {
		return this.s;
	}
}
