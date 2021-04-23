import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		SQVisuaL sql = new SQVisuaL();
		Scanner s = new Scanner(System.in);
		String b;
		//Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		//System.out.println(size);
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
					System.out.println("Please enter the hostname.");
					cred.set(0, s.nextLine());
					System.out.println("Please enter the port number. (Default is 3306)");
					b = s.nextLine();
					if(b.length() == 0) b = "3306";
					cred.set(1, b);
					System.out.println("Please enter the database name.");
					cred.set(2, s.nextLine());
					System.out.println("Please enter the username.");
					cred.set(3, s.nextLine());
					System.out.println("Please enter the password.");
					cred.set(4, s.nextLine());
					sql.setCred(cred);
				} while(!sql.connect());
			}
		}
		System.out.println("Goodbye.");
		s.close();
	}
}