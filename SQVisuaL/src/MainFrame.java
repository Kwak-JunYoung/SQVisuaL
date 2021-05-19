import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	public JTable table;
	public JButton newTable, searchData, insertData;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel;
	private SQVisuaL sql;
	private JScrollPane scrollPane;
	private MainFrame mf;
	public MainFrame(SQVisuaL sql) {
		this.sql = sql;
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
		
		table = new JTable();
		//panel_1.add(table);
		
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Query that was executed recently will show up here");
		panel_2.add(lblNewLabel);
	}
	public void setTable(ArrayList<String[]> table) {
		((DefaultTableModel) this.table.getModel()).setColumnCount(0);
		int r = table.size() - 1, c = table.get(0).length;
		for(int i = 0; i < c; i++) ((DefaultTableModel)this.table.getModel()).addColumn(table.get(0)[i]);
		for(int i = 0; i < r; i++) {
			String[] row = new String[c];
			int k = 0;
			for(int j = 0; j < c; j++) row[k++] = table.get(i)[j];
			((DefaultTableModel)this.table.getModel()).addRow(row);
		}
	}
	public void setTable(ArrayList<String[]> table, ArrayList<Integer> columnsToShow) {
		((DefaultTableModel) this.table.getModel()).setColumnCount(0);
		int r = table.size() - 1;
		for(Integer i : columnsToShow) ((DefaultTableModel)this.table.getModel()).addColumn(table.get(0)[i]);
		for(int i = 0; i < r; i++) {
			String[] row = new String[columnsToShow.size()];
			int k = 0;
			for(Integer j : columnsToShow) row[k++] = table.get(i)[j];
			((DefaultTableModel)this.table.getModel()).addRow(row);
		}
	}
	public void updateTable(String currentTable) {
		table.clearSelection();
		if (table.isEditing()) table.getCellEditor().stopCellEditing();
		String q = "SELECT * FROM " + currentTable + ";";
		ResultSet r = sql.getProvider().query(q);
		ResultSet sch = sql.getProvider().query("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + currentTable + "';");
		ArrayList<String[]> table = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<>();
		
		if(sch != null) {
			try {
				while(sch.next()) temp.add(sch.getString(1));
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		String[] arr = new String[temp.size()];
		arr = temp.toArray(arr);
		table.add(arr);
		if(r != null) {
			int rowSize = temp.size();
			try {
				while(r.next()) {
					String[] row = new String[rowSize];
					for(int i = 0; i < rowSize; i++) row[i] = r.getString(i + 1);
					table.add(row);
				}
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		this.setTable(table);
	}

}
