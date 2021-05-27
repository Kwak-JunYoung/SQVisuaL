import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.sql.*;
class StartFrame extends JFrame {
	JButton MySQL_B, SQLite_B;

	public StartFrame() {
		super("SQVisuaL");
		this.setSize(300, 200);
		this.setLayout(null);
		JDialog d = new JDialog(this);
		JLabel l;
		l = new JLabel("Please select your database type");
		l.setBounds(40, 20, 200, 30);
		this.add(l);

		MySQL_B = new JButton("MySQL");
		MySQL_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException err) {
					System.out.println("Class loading failed. Exiting.");
					System.exit(0);
				}
			}
		});
		MySQL_B.setBounds(5, 130, 100, 30);
		this.add(MySQL_B);

		SQLite_B = new JButton("SQLite");
		SQLite_B.setBounds(180, 130, 100, 30);
		SQLite_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException err) {
					System.out.println("Class loading failed. Exiting.");
					System.exit(0);
				}
			}
		});
		this.add(SQLite_B);
	}
}
