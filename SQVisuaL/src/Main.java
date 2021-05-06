import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		SQVisuaL sql = new SQVisuaL();
		//Scanner s = new Scanner(System.in);
		String b;
		StartFrame st = new StartFrame();
		st.setVisible();		
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
			} else if(sql.getDPType() == "MySQL") {
				for(int i = 0; i < 5; i++) cred.add(null);
				do {
					System.out.println("You have chosen MySQL DB.");
					System.out.println("Please enter the hostname."); //covrt.co
					//cred.set(0, s.nextLine());
					System.out.println("Please enter the port number. (Default is 3306)");
					//b = s.nextLine();
					//if(b.length() == 0) b = "3306";
					//cred.set(1, b);
					System.out.println("Please enter the database name."); //javaprojectdb
					//cred.set(2, s.nextLine());
					System.out.println("Please enter the username."); //javaproject
					//cred.set(3, s.nextLine());
					System.out.println("Please enter the password."); //javaproject
					//cred.set(4, s.nextLine());
					sql.setCred(cred);
				} while(!sql.connect());
				/*ResultSet r = sql.getProvider().query("SELECT * FROM `shoppinglist`"); //Temporary test code
				if(r != null) {
					try {
						while(r.next()) {
							System.out.println(r.getInt(1) + " " + r.getString(2) + " " + r.getInt(3) + " " + r.getDouble(4));  
						}
					} catch(SQLException e) {
						
					}
				}*/
			}
		}
		System.out.println("Goodbye.");
		//s.close();
	}
}
