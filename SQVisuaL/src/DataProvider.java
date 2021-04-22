public abstract class DataProvider {
	boolean isOpen;
	public boolean isOpen() {
		return this.isOpen;
	}
	public abstract boolean connect();
	public abstract void close();
}
