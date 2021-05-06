import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.*;
class StartFrame extends JFrame implements ActionListener{
	public StartFrame() {
		super("SQVisuaL");
	    JDialog d = new JDialog(this);
	    JLabel l;
		l = new JLabel("Please select your database type");
	    l.setBounds(40,20,200,30);
	    this.add(l);
	    
	    JButton MySQL_B = new JButton("MySQL");
	    MySQL_B.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException err) {
					System.out.println("Class loading failed. Exiting.");
					System.exit(0);
				}
				MySQLConnFrame f = new MySQLConnFrame();
				f.setVisible();
            }
	    });
	    MySQL_B.setBounds(5,130,100,30);  
	    this.add(MySQL_B);
	    
	    JButton SQLite_B = new JButton("SQLite");  
	    SQLite_B.setBounds(180,130,100,30);
	    SQLite_B.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException err) {
					System.out.println("Class loading failed. Exiting.");
					return;
				}
				//SQLiteConnFrame f = new SQLiteConnFrame();
            }
	    });
	    this.add(SQLite_B);
	}
	public void setVisible() {
		this.setSize(300, 200);  
		this.setLayout(null);  
		this.setVisible(true);
	}
	public void close() {
		this.dispose();
		this.setVisible(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
