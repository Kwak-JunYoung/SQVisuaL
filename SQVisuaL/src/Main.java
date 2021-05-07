import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		SQVisuaL sql = new SQVisuaL();
		String b;
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
		StartFrame st = new StartFrame();
		st.setVisible(true);
		MySQLConnFrame mscf = new MySQLConnFrame();
		SQLiteConnFrame slcf = new SQLiteConnFrame();
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
		/*try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}*/
		while(!sql.isConnected()) {
			/*do {
				System.out.println("Please choose a database type:");
				System.out.println("1: SQLite, 2: Remote MySQL Server");
			} while(!sql.setProvider(s.nextInt()));
			s.nextLine();*/
			ArrayList<String> cred = new ArrayList<>();
			if(sql.getDPType() == "SQLite") {
				cred.add(null);
				do {
					System.out.println("You have chosen SQLite DB.");
					System.out.println("Please enter the SQLite file you wish to open.");
					//cred.set(0, s.nextLine());
					sql.setCred(cred);
				} while(!sql.connect());
			}
		}
		System.out.println("Goodbye.");
		//s.close();
	}
	public static void testF() {
		System.out.println("DONE");
	}
}
