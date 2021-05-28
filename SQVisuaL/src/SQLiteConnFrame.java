import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class SQLiteConnFrame extends JFrame {
	public JTextField file;
	public JButton cancel, confirm;

	public SQLiteConnFrame() {
		super("SQVisuaL - SQLite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		cancel = new JButton("Cancel");
		panel.add(cancel);

		confirm = new JButton("Confirm");
		panel.add(confirm);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("File Location");
		panel_1.add(lblNewLabel);

		file = new JTextField();
		panel_1.add(file);
		//file.setText("testdb.db");
		file.setColumns(10);
	}
}
