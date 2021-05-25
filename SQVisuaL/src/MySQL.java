import java.sql.*;
import java.util.ArrayList;  
public final class MySQL extends DataProvider {
	//Connection c;
	String host;
	int port;
	String db;
	String username;
	String password;

	@Override
	public boolean connect() {
		try {
			String uri = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db;
			this.c = DriverManager.getConnection(uri, this.username, this.password);
			//System.out.println("Connection to MySQL database has been established.");
            this.isOpen = true;
            return true;
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public int updateQuery(String q) {
		try {
			Statement s = this.c.createStatement(); //TODO: Use prepareStatements to sanitise inputs. https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
			return s.executeUpdate(q);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}  
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public void setDB(String dbname) {
		this.db = dbname;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public ArrayList<MetaRow> getTableInfo(String table) {
		Statement s;
		ArrayList<MetaRow> rows = new ArrayList<>();
		try {
			s = this.c.createStatement();
			ResultSet r = s.executeQuery("SELECT COLUMN_NAME, IS_NULLABLE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, COLUMN_KEY FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table + "';");
			if(r != null) {
				try {
					while(r.next()) {
						rows.add(new MetaRow(r.getString(1), (r.getString(2).equals("NO") ? false : true), r.getString(3), r.getInt(4), r.getString(5)));
					}
				} catch(SQLException e1) {
					e1.printStackTrace();
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public ArrayList<String> getTables() {
		ArrayList<String> tables = new ArrayList<>();
		ResultSet r;
		try {
			Statement s = this.c.createStatement();
			r = s.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema NOT IN ('information_schema', 'mysql', 'performance_schema')");
			if(r != null) while(r.next()) tables.add(r.getString(1));  
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return tables;
	}
}