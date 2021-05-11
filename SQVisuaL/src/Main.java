import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		SQVisuaL sql = new SQVisuaL();
		String b;
		MainFrame mf = new MainFrame();
		StartFrame st = new StartFrame();
		st.setVisible(true);
		MySQLConnFrame mscf = new MySQLConnFrame();
		SQLiteConnFrame slcf = new SQLiteConnFrame();
		SearchDataFrame sdf = new SearchDataFrame();
		InsertDataFrame idf = new InsertDataFrame();
		st.MySQL_B.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        st.setVisible(false);
		        mscf.setVisible(true);
		    }
		});
		st.SQLite_B.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        st.setVisible(false);
		        slcf.setVisible(true);
		    }
		});
		mscf.cancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	st.setVisible(true);
		        mscf.setVisible(false);
		    }
		});
		mscf.confirm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	mscf.setEnabled(false);
		    	ArrayList<String> cred = new ArrayList<>();
		    	cred.add(mscf.host.getText());
		    	cred.add(mscf.port.getText());
		    	cred.add(mscf.db.getText());
		    	cred.add(mscf.user.getText());
		    	cred.add(mscf.pw.getText());
		    	sql.setProvider("MySQL");
		    	sql.setCred(cred);
		    	sql.connect();
		    	System.out.println(sql.isConnected());
		    	if(sql.connect()) {
		    		mf.setVisible(true);
		    		mscf.setVisible(false);
		    		ResultSet r = sql.getProvider().query("SELECT * FROM `shoppinglist`"); //Temporary test code
					if(r != null) {
						try {
							while(r.next()) {
								System.out.println(r.getInt(1) + " " + r.getString(2) + " " + r.getInt(3) + " " + r.getDouble(4));  
							}
						} catch(SQLException e) {
							
						}
					}
		    	}
		    	mscf.setEnabled(true);
		    }
		});
		slcf.cancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	st.setVisible(true);
		        slcf.setVisible(false);
		    }
		});
		slcf.confirm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	slcf.setEnabled(false);
		    	ArrayList<String> cred = new ArrayList<>();
		    	cred.set(0, slcf.file.getText());
		    	sql.setProvider("SQLite");
		    	sql.setCred(cred);
		    	sql.connect();
		    	System.out.println(sql.isConnected());
		    	if(sql.connect()) {
		    		mf.setVisible(true);
		    		mscf.setVisible(false);
		    		ResultSet r = sql.getProvider().query("SELECT * FROM `chinook`"); //Temporary test code
					if(r != null) {
						try {
							while(r.next()) {
								System.out.println(r.getInt(1) + " " + r.getString(2) + " " + r.getInt(3) + " " + r.getDouble(4));  
							}
						} catch(SQLException e) {
							
						}
					}
		    	}
		    	slcf.setEnabled(true);
		    }
		});
		mf.searchData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sdf.setVisible(true);
			}
		});
		mf.insertData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idf.setVisible(true);
			}
		});
	}
	public static void testF() {
		System.out.println("DONE");
	}
}
