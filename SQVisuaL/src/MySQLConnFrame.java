import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
class MySQLConnFrame extends JFrame implements ActionListener{
	public JTextField host, port, user, pw, db;
	public MySQLConnFrame() {
	    super("SQVisuaL - MySQL");
	    JLabel l;

	    l = new JLabel("Hostname");
	    l.setBounds(40,20,100,30);
	    this.add(l);
	    
	    host = new JTextField();
	    host.setBounds(140, 20, 150, 30);
	    this.add(host);
	    
	    l = new JLabel("Port");
	    l.setBounds(40,60,100,30);
	    this.add(l);
	    
	    port = new JTextField();
	    port.setBounds(140, 60, 150, 30);
	    this.add(port);
	    
	    l = new JLabel("Username");
	    l.setBounds(40,100,100,30);
	    this.add(l);
	    
	    user = new JTextField();
	    user.setBounds(140, 100, 150, 30);
	    this.add(user);
	    
	    l = new JLabel("Password");
	    l.setBounds(40,140,100,30);
	    this.add(l);
	    
	    pw = new JTextField();
	    pw.setBounds(140, 140, 150, 30);
	    this.add(pw);
	    
	    l = new JLabel("Database Name");
	    l.setBounds(40,180,100,30);
	    this.add(l);
	    
	    db = new JTextField();
	    db.setBounds(140, 180, 150, 30);
	    this.add(db);
	    
	    JButton confirm = new JButton("Confirm");  
	    confirm.setBounds(250, 290, 100, 30);  
	    this.add(confirm);
	    
	    JButton cancel = new JButton("Cancel");  
	    cancel.setBounds(5, 290, 100, 30);  
	    this.add(cancel);
	}
	public void setVisible() {
		this.setSize(410, 400);
		this.setLayout(null);
		super.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
