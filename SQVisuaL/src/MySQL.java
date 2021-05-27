import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public final class MySQL extends DataProvider {
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
			this.isOpen = true;
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "SQL Error",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	@Override
	public void close() {
		try {
			this.c.close();
			this.isOpen = false;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "SQL Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public ResultSet query(String q) {
		try {
			Statement s = this.c.createStatement(); // TODO: Use prepareStatements to sanitise inputs.
													// https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
			ResultSet r = s.executeQuery(q);
			return r;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "SQL Error",
					JOptionPane.INFORMATION_MESSAGE);
			return null;
		}

	}

	public int updateQuery(String q) {
		try {
			Statement s = this.c.createStatement(); // TODO: Use prepareStatements to sanitise inputs.
													// https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
			return s.executeUpdate(q);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "SQL Error",
					JOptionPane.INFORMATION_MESSAGE);
			return 0;
		}
	}

	public SQLException updateQueryReturnErr(String q) {
		try {
			Statement s = this.c.createStatement(); // TODO: Use prepareStatements to sanitise inputs.
													// https://stackoverflow.com/questions/1812891/java-escape-string-to-prevent-sql-injection
			s.executeUpdate(q);
			return null;
		} catch (SQLException e) {
			return e;
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
			ResultSet r = s.executeQuery(
					"SELECT COLUMN_NAME, IS_NULLABLE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, COLUMN_KEY FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
							+ table + "';");
			if (r != null) {
				try {
					while (r.next()) {
						boolean canBeNull;
						if (r.getString(2).equals("NO") || r.getString(5).equals("PRI"))
							canBeNull = false;
						else
							canBeNull = true;
						rows.add(new MetaRow(r.getString(1), canBeNull, r.getString(3), r.getInt(4), r.getString(5)));
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "SQL Error",
							JOptionPane.INFORMATION_MESSAGE);
					return null;
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "SQL Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return rows;
	}

	@Override
	public ArrayList<String> getTables() {
		ArrayList<String> tables = new ArrayList<>();
		ResultSet r;
		try {
			Statement s = this.c.createStatement();
			r = s.executeQuery(
					"SELECT table_name FROM information_schema.tables WHERE table_schema NOT IN ('information_schema', 'mysql', 'performance_schema')");
			if (r != null)
				while (r.next())
					tables.add(r.getString(1));
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "SQL Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return tables;
	}
}