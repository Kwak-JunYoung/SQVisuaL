import javax.swing.*;
public class StartFrame{
	public JFrame f;
	public StartFrame() {
	    f = new JFrame("SQVisuaL");
	    JLabel l;
		l = new JLabel("Please select your database type");
	    l.setBounds(40,20,200,30);
	    f.add(l);
	    
	    JButton MySQL_B = new JButton("MySQL");  
	    MySQL_B.setBounds(5,130,100,30);  
	    f.add(MySQL_B);
	    
	    JButton SQLite_B = new JButton("SQLite");  
	    SQLite_B.setBounds(180,130,100,30);  
	    f.add(SQLite_B);
	}
	public void setVisible() {
		f.setSize(300, 200);  
	    f.setLayout(null);  
	    f.setVisible(true);
	}
	public void close() {
		f.setVisible(false);
		f.dispose();
	}
}
