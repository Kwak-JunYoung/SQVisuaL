import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	public JTable table;
	public JButton newTable, searchData, insertData;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel;
	private JTable table_1;
	public MainFrame() {
		setTitle("SQVisuaL - <Hostname ¶Ç´Â File Location>");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		newTable = new JButton("Add new table");
		panel.add(newTable);
		
		searchData = new JButton("Search for data");
		panel.add(searchData);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel.add(btnNewButton_3);
		
		insertData = new JButton("Insert new record");
		panel.add(insertData);
		
		label = new JLabel("");
		panel.add(label);
		
		label_1 = new JLabel("");
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		String data[][]={ {"Key","Price", "Name", "Department", "Count"} };    
		String column[]={"Column Name","Data Type","Primary Key?", "Can be NULL?", "Must be unique?"};
		table = new JTable(data, column);
		panel_1.add(table);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Query that was executed recently will show up here");
		panel_2.add(lblNewLabel);
	}

}
