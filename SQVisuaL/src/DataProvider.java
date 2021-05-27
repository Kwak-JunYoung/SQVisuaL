import java.sql.*;
import java.util.ArrayList;

public abstract class DataProvider {
	boolean isOpen;
	Connection c;

	public boolean isOpen() {
		return this.isOpen;
	}

	public abstract boolean connect();

	public abstract void close();

	public abstract ResultSet query(String q);

	public abstract int updateQuery(String q);

	public abstract ArrayList<MetaRow> getTableInfo(String table);

	public abstract ArrayList<String> getTables();

	public abstract SQLException updateQueryReturnErr(String q);
}
