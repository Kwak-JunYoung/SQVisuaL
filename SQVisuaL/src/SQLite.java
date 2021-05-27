import java.sql.*;
import java.util.ArrayList;

public class SQLite extends DataProvider{
	String path;
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
	@Override
	public ResultSet query(String q) {
		try {
			Statement s = this.c.createStatement(); //TODO: Use prepareStatements to sanitise inputs. https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
			ResultSet r = s.executeQuery(q);
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	@Override
	public int updateQuery(String q) {
		try {
			Statement s = this.c.createStatement(); //TODO: Use prepareStatements to sanitise inputs. https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
			return s.executeUpdate(q);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}  
	}
	@Override
	public SQLException updateQueryReturnErr(String q) {
		try {
			Statement s = this.c.createStatement(); //TODO: Use prepareStatements to sanitise inputs. https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
			s.executeUpdate(q);
			return null;
		} catch (SQLException e) {
			return e;
		}  
	}
	@Override
	public ArrayList<MetaRow> getTableInfo(String table) {
		ArrayList<MetaRow> rows = new ArrayList<>();
		try {
			Statement s = this.c.createStatement(); //TODO: Use prepareStatements to sanitise inputs. https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
			ResultSet r = s.executeQuery("PRAGMA table_info(" + table + ");");
			if(r != null) {
				try {
					while(r.next()) {
						rows.add(new MetaRow(r.getString(2), (r.getInt(4) != 0 ? true : false), r.getString(3), -1, (r.getInt(6) == 1 ? "PRI" : "")));
					}
				} catch(SQLException e1) {
					e1.printStackTrace();
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rows; 
	}
	@Override
	public ArrayList<String> getTables() {
		ArrayList<String> tables = new ArrayList<>();
		ResultSet r;
		try {
			Statement s = this.c.createStatement();
			r = s.executeQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';");
			if(r != null) while(r.next()) tables.add(r.getString(1));  
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return tables;
	}
}
