import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
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
				switch(column) {
				case 1:
					if(this.getModel().getValueAt(row, 2) == null) return true;
					else if(this.getModel().getValueAt(row, 2).equals("Set to NULL")) return false;
					return true;
				case 2:
					if(this.getModel().getValueAt(row, 2) == null) return true;
					else if(this.getModel().getValueAt(row, 2).equals("Unavailable")) return false;
					return true;
				default:
					return false;
				}           
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
	    			if(model.getValueAt(i, 2).equals("Set to NULL")) data += "NULL";
	    			else data += "'" + (String) model.getValueAt(i, 1) + "'";
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
	    			mf.setTable();
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
			    	ArrayList<MetaRow> arr = sql.getProvider().getTableInfo(currentTable);
			    	for(MetaRow r : arr) {
			    		Object[] row;
						if(!r.canBeNull()) {
							row = new Object[]{r.getName(), "", "Unavailable", r.getType(), (r.getLen() != -1 ? r.getLen() : ""), r.getKeyStatus()};
							model.addRow(row);
						}
						else {
							JComboBox<String> n = new JComboBox<String>();
				    		n.addItem("Set to NULL");
				    		n.addItem("Not NULL");
				    		row = new Object[]{r.getName(), "", null, r.getType(), (r.getLen() != -1 ? r.getLen() : ""), r.getKeyStatus()};
				    		model.addRow(row);
				    		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(n));
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
			ArrayList<String> tables = sql.getProvider().getTables();
			tables.add(0, "Please select a table...");
			if(tables.size() != 0) t = tables;
    	}
	}
}
