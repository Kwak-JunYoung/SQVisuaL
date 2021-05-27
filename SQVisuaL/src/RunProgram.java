import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RunProgram {
	InsertDataFrame idf;
	SQVisuaL sql;
	ArrayList<String> tableList;
	SearchDataFrame sdf;

	public void run() {
		sql = new SQVisuaL();
		MainFrame mf = new MainFrame(sql);
		StartFrame st = new StartFrame();
		st.setVisible(true);
		MySQLConnFrame mscf = new MySQLConnFrame();
		SQLiteConnFrame slcf = new SQLiteConnFrame();
		sdf = null;
		AddTable at = new AddTable(mf, sql);
		idf = null;// = new InsertDataFrame();
		st.MySQL_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st.setVisible(false);
				mscf.setVisible(true);
			}
		});
		st.SQLite_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st.setVisible(false);
				slcf.setVisible(true);
			}
		});
		mscf.cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st.setVisible(true);
				mscf.setVisible(false);
			}
		});
		mscf.confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				mscf.setEnabled(false);
				ArrayList<String> cred = new ArrayList<>();
				cred.add(mscf.host.getText());
				cred.add(mscf.port.getText());
				cred.add(mscf.db.getText());
				cred.add(mscf.user.getText());
				cred.add(mscf.pw.getText());
				sql.setProvider("MySQL");
				sql.setCred(cred);
				sql.connect();
				if (sql.connect()) {
					mf.setVisible(true);
					mscf.setVisible(false);
					mf.setTitle("SQVisuaL - " + mscf.host.getText());
					if (sql.getProvider().getTables().size() != 0) {
						mf.updateTable(sql.getProvider().getTables().get(0));
						mf.setTable();
					}
				}
				mscf.setEnabled(true);
			}
		});
		slcf.cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st.setVisible(true);
				slcf.setVisible(false);
			}
		});
		slcf.confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				slcf.setEnabled(false);
				ArrayList<String> cred = new ArrayList<>();
				cred.add(slcf.file.getText());
				sql.setProvider("SQLite");
				sql.setCred(cred);
				sql.connect();
				if (sql.connect()) {
					mf.setVisible(true);
					slcf.setVisible(false);
					mf.setTitle("SQVisuaL - " + slcf.file.getText());
					if (sql.getProvider().getTables().size() != 0) {
						mf.updateTable(sql.getProvider().getTables().get(0));
						mf.setTable();
					}
				}
				slcf.setEnabled(true);
			}
		});
		mf.searchData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sdf = new SearchDataFrame(sql, mf);
				sdf.setVisible(true);
			}
		});
		mf.insertData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idf = new InsertDataFrame(sql, mf);
				idf.setVisible(true);
			}
		});
		mf.newTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				at.setVisible(true);
			}
		});
	}
}
