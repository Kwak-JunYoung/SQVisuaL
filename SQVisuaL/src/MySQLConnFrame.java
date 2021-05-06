import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class MySQLConnFrame{
	public JTextField host, port, user, pw, db;
	public JFrame f;
	public MySQLConnFrame() {
	    f = new JFrame("SQVisuaL - MySQL");
	    JLabel l;

	    l = new JLabel("Hostname");
	    l.setBounds(40,20,100,30);
	    f.add(l);
	    
	    host = new JTextField();
	    host.setBounds(140, 20, 150, 30);
	    f.add(host);
	    
	    l = new JLabel("Port");
	    l.setBounds(40,60,100,30);
	    f.add(l);
	    
	    port = new JTextField();
	    port.setBounds(140, 60, 150, 30);
	    f.add(port);
	    
	    l = new JLabel("Username");
	    l.setBounds(40,100,100,30);
	    f.add(l);
	    
	    user = new JTextField();
	    user.setBounds(140, 100, 150, 30);
	    f.add(user);
	    
	    l = new JLabel("Password");
	    l.setBounds(40,140,100,30);
	    f.add(l);
	    
	    pw = new JTextField();
	    pw.setBounds(140, 140, 150, 30);
	    f.add(pw);
	    
	    l = new JLabel("Database Name");
	    l.setBounds(40,180,100,30);
	    f.add(l);
	    
	    db = new JTextField();
	    db.setBounds(140, 180, 150, 30);
	    f.add(db);
	    
	    JButton confirm = new JButton("Confirm");  
	    confirm.setBounds(250, 290, 100, 30);  
	    f.add(confirm);
	    
	    JButton cancel = new JButton("Cancel");  
	    cancel.setBounds(5, 290, 100, 30);  
	    f.add(cancel);
	}
	public void setVisible() {
		f.setSize(410, 400);
		f.setLayout(null);
		f.setVisible(true);
	}
	public void close() {
		f.setVisible(false);
		f.dispose();
	}
}
