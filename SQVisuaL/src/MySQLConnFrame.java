import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
class MySQLConnFrame extends JFrame{
	public JTextField host, port, user, pw, db;
	public JButton cancel, confirm;
	public MySQLConnFrame() {
		super("SQVisuaL - MySQL");
		getContentPane().setLayout(new GridLayout(6, 1));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Hostname");
		lblNewLabel.setPreferredSize(new Dimension(80,10));
		panel.add(lblNewLabel);
		
		host = new JTextField();
		panel.add(host);
		host.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Port");
		lblNewLabel_1.setPreferredSize(new Dimension(80, 10));
		panel_1.add(lblNewLabel_1);
		
		port = new JTextField();
		port.setColumns(20);
		panel_1.add(port);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setPreferredSize(new Dimension(80, 10));
		panel_2.add(lblNewLabel_2);
		
		user = new JTextField();
		user.setColumns(20);
		panel_2.add(user);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setPreferredSize(new Dimension(80, 10));
		panel_3.add(lblNewLabel_3);
		
		pw = new JTextField();
		pw.setColumns(20);
		panel_3.add(pw);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4);
		
		JLabel lblNewLabel_4 = new JLabel("Database");
		lblNewLabel_4.setPreferredSize(new Dimension(80, 10));
		panel_4.add(lblNewLabel_4);
		
		db = new JTextField();
		db.setColumns(20);
		panel_4.add(db);
		
		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5);
		
		cancel = new JButton("Cancel");
		panel_5.add(cancel);
		
		confirm = new JButton("Confirm");
		panel_5.add(confirm);
		
		host.setText("covrt.co");
		port.setText("3306");
		user.setText("javaproject");
		pw.setText("javaproject");
		db.setText("javaprojectdb");
		
		this.setSize(410, 400);
	}
}
