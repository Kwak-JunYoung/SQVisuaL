import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RunProgram {
	InsertDataFrame idf;
	public void run() {
		SQVisuaL sql = new SQVisuaL();
		MainFrame mf = new MainFrame();
		StartFrame st = new StartFrame();
		st.setVisible(true);
		MySQLConnFrame mscf = new MySQLConnFrame();
		SQLiteConnFrame slcf = new SQLiteConnFrame();
		SearchDataFrame sdf = new SearchDataFrame();
		AddTable at = new AddTable();
		SearchDataAddFrame sdaf = new SearchDataAddFrame();
		idf = null;// = new InsertDataFrame();
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
		    	if(sql.connect()) {
		    		mf.setVisible(true);
		    		mscf.setVisible(false);
		    		ResultSet r = sql.getProvider().query("SELECT table_name FROM information_schema.tables WHERE table_schema NOT IN ('information_schema', 'mysql', 'performance_schema')");
		    		if(r != null) {
		    			ArrayList<String> tables = new ArrayList<>();
						try {
							while(r.next()) {
								tables.add(r.getString(1));  
							}
						} catch(SQLException e) {
							
						}
						if(tables.size() != 0) setTableList(tables);
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
		mf.newTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				at.setVisible(true);
			}
		});
		
		at.Cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				at.setVisible(false);
			}
		});	
		
		sdf.add.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             sdaf.setVisible(true);
	             sdf.setVisible(false);
	          }
	      });

	}
	public void setTableList(ArrayList<String> tables) {
		this.idf = new InsertDataFrame(tables);
	}
}
