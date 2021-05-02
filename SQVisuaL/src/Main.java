import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
public class Main {
	public static void main(String[] args) {
		SQVisuaL sql = new SQVisuaL();
		Scanner s = new Scanner(System.in);
		String b;
		JFrame f = new JFrame();
	    /*JFrame f=new JFrame("SQVisuaL");
	    JLabel l;
	    
	    l = new JLabel("Please select your database type");
	    l.setBounds(40,20,200,30);
	    f.add(l);
	    
	    JButton MySQL_B=new JButton("MySQL");  
	    MySQL_B.setBounds(5,130,100,30);  
	    f.add(MySQL_B);
	    
	    JButton SQLite_B=new JButton("SQLite");  
	    SQLite_B.setBounds(180,130,100,30);  
	    f.add(SQLite_B);
	    
	    f.setSize(300,200);  
	    f.setLayout(null);  
	    f.setVisible(true);*/  
		
	   /* JFrame f=new JFrame("SQVisuaL-MySQL");
	    JLabel l;
	    JTextField t1, t2, t3, t4, t5;
	    
	    l = new JLabel("Hostname");
	    l.setBounds(40,20,100,30);
	    f.add(l);
	    
	    t1 = new JTextField("covrt.co");
	    t1.setBounds(140, 20, 150, 30);
	    f.add(t1);
	    
	    l = new JLabel("Port");
	    l.setBounds(40,60,100,30);
	    f.add(l);
	    
	    t2 = new JTextField("3306");
	    t2.setBounds(140, 60, 150, 30);
	    f.add(t2);
	    
	    l = new JLabel("Username");
	    l.setBounds(40,100,100,30);
	    f.add(l);
	    
	    t1 = new JTextField("javaproject");
	    t1.setBounds(140, 100, 150, 30);
	    f.add(t1);
	    
	    l = new JLabel("Password");
	    l.setBounds(40,140,100,30);
	    f.add(l);
	    
	    t1 = new JTextField("*********** (javaproject)");
	    t1.setBounds(140, 140, 150, 30);
	    f.add(t1);
	    
	    l = new JLabel("Database Name");
	    l.setBounds(40,180,100,30);
	    f.add(l);
	    
	    t1 = new JTextField("javaprojectdb");
	    t1.setBounds(140, 180, 150, 30);
	    f.add(t1);
	    
	    JButton MySQL_B=new JButton("MySQL");  
	    MySQL_B.setBounds(5,290,100,30);  
	    f.add(MySQL_B);
	    
	    JButton SQLite_B=new JButton("SQLite");  
	    SQLite_B.setBounds(250,290,100,30);  
	    f.add(SQLite_B);
	    
	    f.setSize(410,400);  
	    f.setLayout(null);  
	    f.setVisible(true); */ 
		try {
			Class.forName("org.sqlite.JDBC");
			//Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class loading failed. Exiting.");
			s.close();
			return;
		}
		while(!sql.isConnected()) {
			do {
				System.out.println("Please choose a database type:");
				System.out.println("1: SQLite, 2: Remote MySQL Server");
			} while(!sql.setProvider(s.nextInt()));
			s.nextLine();
			ArrayList<String> cred = new ArrayList<>();
			if(sql.getDPType() == "SQLite") {
				cred.add(null);
				do {
					System.out.println("You have chosen SQLite DB.");
					System.out.println("Please enter the SQLite file you wish to open.");
					cred.set(0, s.nextLine());
					sql.setCred(cred);
				} while(!sql.connect());
			} else if(sql.getDPType() == "MySQL") {
				for(int i = 0; i < 5; i++) cred.add(null);
				do {
					System.out.println("You have chosen MySQL DB.");
					System.out.println("Please enter the hostname."); //covrt.co
					cred.set(0, s.nextLine());
					System.out.println("Please enter the port number. (Default is 3306)");
					b = s.nextLine();
					if(b.length() == 0) b = "3306";
					cred.set(1, b);
					System.out.println("Please enter the database name."); //javaprojectdb
					cred.set(2, s.nextLine());
					System.out.println("Please enter the username."); //javaproject
					cred.set(3, s.nextLine());
					System.out.println("Please enter the password."); //javaproject
					cred.set(4, s.nextLine());
					sql.setCred(cred);
				} while(!sql.connect());
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
		}
		System.out.println("Goodbye.");
		s.close();
	}
}
