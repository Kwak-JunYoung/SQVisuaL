import java.sql.*;

public class SQLite extends DataProvider{
	String path;
	Connection c;
	public SQLite() {
		this.isOpen = false;
		this.path = null;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public boolean connect() {
		if(path == null) return false;
		this.c = null;
		try {
            String url = "jdbc:sqlite:" + this.path;
            this.c = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            this.isOpen = true;
            return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	@Override
	public void close() {
		try {
			this.c.close();
			this.isOpen = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

/* To import library
 * Project properties -> Source -> Add Source -> Select lib
 * Libraries -> Add JARs -> Select lib/sqlite
 */