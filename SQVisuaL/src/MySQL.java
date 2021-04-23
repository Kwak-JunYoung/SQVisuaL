import java.sql.*;  
public final class MySQL extends DataProvider {
	Connection c;
	String host;
	int port;
	String db;
	String username;
	String password;

	@Override
	public boolean connect() {
		try {
			this.c = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db, this.username, this.password);
			System.out.println("Connection to MySQL database has been established.");
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

}
