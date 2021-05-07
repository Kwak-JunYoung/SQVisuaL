import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
class MySQLConnFrame extends JFrame{
	public JTextField host, port, user, pw, db;
	public JButton confirm, cancel;
	public MySQLConnFrame() {
	    super("SQVisuaL - MySQL");
	    JLabel l;

	    l = new JLabel("Hostname");
	    l.setBounds(40,20,100,30);
	    this.add(l);
	    
	    host = new JTextField();
	    host.setBounds(140, 20, 150, 30);
	    this.add(host);
	    host.setText("covrt.co");
	    
	    l = new JLabel("Port");
	    l.setBounds(40,60,100,30);
	    this.add(l);
	    
	    port = new JTextField();
	    port.setBounds(140, 60, 150, 30);
	    this.add(port);
	    port.setText("3306");
	    
	    l = new JLabel("Username");
	    l.setBounds(40,100,100,30);
	    this.add(l);
	    
	    user = new JTextField();
	    user.setBounds(140, 100, 150, 30);
	    this.add(user);
	    user.setText("javaproject");
	    
	    l = new JLabel("Password");
	    l.setBounds(40,140,100,30);
	    this.add(l);
	    
	    pw = new JTextField();
	    pw.setBounds(140, 140, 150, 30);
	    this.add(pw);
	    pw.setText("javaproject");
	    
	    l = new JLabel("Database Name");
	    l.setBounds(40,180,100,30);
	    this.add(l);
	    
	    db = new JTextField();
	    db.setBounds(140, 180, 150, 30);
	    this.add(db);
	    db.setText("javaprojectdb");
	    
	    confirm = new JButton("Confirm");  
	    confirm.setBounds(250, 290, 100, 30);  
	    this.add(confirm);
	    
	    cancel = new JButton("Cancel");  
	    cancel.setBounds(5, 290, 100, 30);  
	    this.add(cancel);
	    
	    this.setSize(410, 400);
		this.setLayout(null);
	}
}
