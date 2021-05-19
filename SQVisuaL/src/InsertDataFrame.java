import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

class InsertDataFrame extends JFrame {
	public String currentTable;
	public JTable table;
	public JButton cancel, execute;
	private JComboBox<String> tables;
	private SQVisuaL sql;
	private JLabel status;
	private JCheckBox closeOnExe;
	public ArrayList<String> t;
	public InsertDataFrame(SQVisuaL sql, MainFrame mf) {
		super("SQVisuaL - Insert new data");
		this.sql = sql;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		this.updateTables();
		String[] columns = {"Target attribute", "Value", "Set to NULL", "Data type", "Max length", "Key status"};
		
		table = new JTable(new DefaultTableModel(columns, 0)) {
			public boolean isCellEditable(int row, int column) {                
                return column == 1;               
			}
		};
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 2));
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_1.add(cancel);
		
		execute = new JButton("Execute");
		execute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.clearSelection();
				if (table.isEditing()) table.getCellEditor().stopCellEditing();
				int rc = model.getRowCount();
				String name = "(", data = "(", q = "INSERT INTO `" + currentTable + "` ";
	    		for (int i = 0; i < rc; i++) {
	    			name += "`" + (String) model.getValueAt(i, 0) + "`";
	    			data += "'" + (String) model.getValueAt(i, 1) + "'";
	    			if(i !=  rc - 1) {
	    				name += ", ";
	    				data += ", ";
	    			}
	    		}
	    		name += ")";
	    		data += ")";
	    		q += name;
	    		q += " VALUES ";
	    		q += data;
	    		q += ";";
	    		sql.getProvider().updateQuery(q);
	    		if(closeOnExe.isSelected()) {
	    			setVisible(false);
	    			mf.updateTable(currentTable);
	    		}
			}
		});
		execute.setVisible(false);
		panel_1.add(execute);
		
		closeOnExe = new JCheckBox("Close window on execution");
		panel_1.add(closeOnExe);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		tables = new JComboBox<String>();
		for(String name : t) tables.addItem(name);
		tables.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			    	currentTable = (String) e.getItem();
			    	if(currentTable.equals("Please select a table...")) {
			    		execute.setVisible(false);
			    		return;
			    	}
			    	execute.setVisible(true);
			    	status.setText("Getting table schema. Please wait.");
			    	int rc = model.getRowCount();
		    		for (int i = rc - 1; i >= 0; i--) {
		    		    model.removeRow(i);
		    		}
			    	ResultSet r = sql.getProvider().query("SELECT COLUMN_NAME, IS_NULLABLE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, COLUMN_KEY FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + currentTable + "';");
			    	if(r != null) {
						try {
							while(r.next()) {
								String setToNull = (r.getString(2).equals("NO") ? "Unavailable" : "");
								Object[] row = {r.getString(1), "", setToNull, r.getString(3), r.getString(4), r.getString(5)};
								model.addRow(row);
							}
						} catch(SQLException e1) {
							status.setText("Unexpected error has occurred. Please refer to the log.");
							e1.printStackTrace();
						}
					}
			    	status.setText("Table schema fetched. Please enter the values by double clicking the cells.");
			    }
			}
		});
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(tables);
		
		status = new JLabel("Please choose a table first.");
		panel.add(status, BorderLayout.SOUTH);
	    
	}
	public void updateTables() {
		if(this.sql.connect()) {
    		ResultSet r = sql.getProvider().query("SELECT table_name FROM information_schema.tables WHERE table_schema NOT IN ('information_schema', 'mysql', 'performance_schema')");
    		if(r != null) {
    			ArrayList<String> tables = new ArrayList<>();
    			tables.add("Please select a table...");
				try {
					while(r.next()) {
						tables.add(r.getString(1));  
					}
				} catch(SQLException e) {
					
				}
				if(tables.size() != 0) t = tables;
			}
    	}
	}
}
