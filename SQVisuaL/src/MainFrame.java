import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JFrame {
	public JTable table;
	public JButton newTable, searchData, insertData;
	private String currentTable;
	private ArrayList<Integer> pks, show;
	private ArrayList<String[]> internalTable;
	private JLabel status;
	private SQVisuaL sql;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private boolean allowEdit, internalEdit;
	private JLabel errormsg;
	private String cond;

	public MainFrame(SQVisuaL sql) {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.pks = new ArrayList<>();
		this.show = new ArrayList<>();
		this.internalTable = new ArrayList<String[]>();
		this.sql = sql;
		this.allowEdit = true;
		this.internalEdit = false;
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

		insertData = new JButton("Insert new record");
		panel.add(insertData);

		this.model = new DefaultTableModel();

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		table = new JTable(this.model) {
			public boolean isCellEditable(int row, int column) {
				return allowEdit;
			}
		};
		// table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		this.model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (internalEdit)
					return;
				if (e.getType() != TableModelEvent.UPDATE)
					return;
				if (e.getFirstRow() < 0 || e.getColumn() < 0)
					return;
				changeValue(e.getFirstRow(), e.getColumn(), (String) model.getValueAt(e.getFirstRow(), e.getColumn()));
			}

		});
		panel_1.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);

		errormsg = new JLabel("Any errors on editing this table will be displayed here.");
		panel_1.add(errormsg, BorderLayout.SOUTH);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);

		status = new JLabel("Query that was executed recently will show up here");
		panel_2.add(status);
	}

	public void changeValue(int r, int c, String val) {
		if ((this.internalTable.get(r + 1)[c] == null && val == null)
				|| (this.internalTable.get(r + 1)[c] != null && this.internalTable.get(r + 1)[c].equals(val)))
			return;
		this.allowEdit = false;
		this.updateQuery(this.internalTable.get(r + 1)[c] + " -> " + val);
		String q = "UPDATE `" + this.currentTable + "` SET `" + this.internalTable.get(0)[c] + "` = '" + val
				+ "' WHERE ";
		for (int i = 0; i < this.pks.size(); i++) {
			int col = this.pks.get(i);
			q += "`" + this.currentTable + "`.`" + this.internalTable.get(0)[col] + "` = '"
					+ this.internalTable.get(r + 1)[col] + "'";
			if (i != this.pks.size() - 1)
				q += " AND ";
		}
		SQLException e = sql.getProvider().updateQueryReturnErr(q);
		if (e != null)
			this.errormsg.setText(e.getMessage());
		else
			this.errormsg.setText("Record updated successfully");
		this.updateTable(this.currentTable, this.cond);
		if (this.show.size() == 0)
			this.setTable();
		else
			this.setTable(this.show);
		this.allowEdit = true;
	}

	public void setTable() {
		this.internalEdit = true;
		this.model.setColumnCount(0);
		this.model.setRowCount(0);
		int r = this.internalTable.size(), c = this.internalTable.get(0).length;
		for (int i = 0; i < c; i++)
			this.model.addColumn(this.internalTable.get(0)[i]);
		for (int i = 1; i < r; i++) {
			String[] row = new String[c];
			int k = 0;
			for (int j = 0; j < c; j++)
				row[k++] = this.internalTable.get(i)[j];
			this.model.addRow(row);
		}
		this.internalEdit = false;
	}

	public void setTable(ArrayList<Integer> columnsToShow) {
		this.internalEdit = true;
		this.show = columnsToShow;
		this.model.setColumnCount(0);
		this.model.setRowCount(0);
		int r = this.internalTable.size();
		for (Integer i : columnsToShow)
			this.model.addColumn(this.internalTable.get(0)[i]);
		for (int i = 1; i < r; i++) {
			String[] row = new String[columnsToShow.size()];
			int k = 0;
			for (Integer j : columnsToShow)
				row[k++] = this.internalTable.get(i)[j];
			this.model.addRow(row);
		}
		this.internalEdit = false;
	}

	public void updateQuery(String q) {
		this.status.setText(q);
	}

	public void updateTable(String currentTable) {
		this.updateTable(currentTable, null);
	}

	public void updateTable(String currentTable, String cond) {
		this.currentTable = currentTable;
		this.cond = cond;
		table.clearSelection();
		String q = "SELECT * FROM " + currentTable;
		if (cond != null)
			q += " WHERE " + cond + ";";

		ResultSet r = sql.getProvider().query(q);
		ArrayList<String[]> table = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<MetaRow> rows = sql.getProvider().getTableInfo(currentTable);
		this.pks.clear();
		this.internalTable.clear();
		int c = 0;
		for (MetaRow row : rows) {
			if (row.getKeyStatus().equals("PRI"))
				this.pks.add(c);
			temp.add(row.getName());
			c++;
		}
		String[] arr = new String[temp.size()];
		arr = temp.toArray(arr);
		table.add(arr);
		if (r != null) {
			this.updateQuery(q);
			int rowSize = temp.size();
			try {
				while (r.next()) {
					String[] row = new String[rowSize];
					for (int i = 0; i < rowSize; i++)
						row[i] = r.getString(i + 1);
					table.add(row);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		this.internalTable = table;
	}
}
