import java.sql.*;
public abstract class DataProvider {
	boolean isOpen;
	public boolean isOpen() {
		return this.isOpen;
	}
	public abstract boolean connect();
	public abstract void close();
	public abstract ResultSet query(String q);
}
