import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SearchDataFrame extends JFrame {
	public ArrayList<String> t;
	public JTable table;
	public JButton add, delete, cancel, execute;
	public DefaultTableModel model;
	private JScrollPane scrollPane;
	private JComboBox<String> tables;
	private SQVisuaL sql;
	private SearchDataAddFrame sdaf;
	private String currentTable;
	private JLabel status;
	private ArrayList<Constraint> constr;

	public SearchDataFrame(SQVisuaL sql, MainFrame mf) {

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.sql = sql;
		this.updateTables();
		this.constr = new ArrayList<Constraint>();

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(2, 2));

		add = new JButton("Add Constraint");
		panel_1.add(add);
		add.addActionListener(new addButtonClickListener());

		delete = new JButton("Delete Constraint");
		panel_1.add(delete);
		delete.addActionListener(new deleteButtonClickListener());

		cancel = new JButton("Cancel");
		panel_1.add(cancel);
		cancel.addActionListener(new cancelButtonClickListener());

		execute = new JButton("Execute");
		panel_1.add(execute);
		execute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (constr.size() == 0)
					mf.updateTable(currentTable);
				else {
					String cond = "";
					for (int i = 0; i < constr.size(); i++) {
						cond += constr.get(i).toString();
						if (i != constr.size() - 1)
							cond += " " + table.getValueAt(i, 2) + " ";
					}
					mf.updateTable(currentTable, cond);
				}
				mf.setTable();
				setVisible(false);
			}

		});

		String[] columns = { "Column Name", "Constraint", "Connector" };
		table = new JTable(new DefaultTableModel(columns, 0)) {
			public boolean isCellEditable(int row, int column) {
				if (column != 2)
					return false;
				else if (this.getModel().getRowCount() - 1 == row)
					return false;
				else
					return true;
			}
		};
		model = (DefaultTableModel) table.getModel();

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		tables = new JComboBox<String>();
		panel_2.add(tables);

		status = new JLabel("Current status will be displayed here.");
		panel_2.add(status, BorderLayout.SOUTH);
		for (String name : this.t)
			tables.addItem(name);
		this.setButtonVisibility(false);
		tables.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					currentTable = (String) e.getItem();
					if (currentTable.equals("Please select a table...")) {
						setButtonVisibility(false);
						return;
					}
					setButtonVisibility(true);
					status.setText("Getting table schema. Please wait.");
					int rc = model.getRowCount();
					for (int i = rc - 1; i >= 0; i--)
						model.removeRow(i);
					setSDAF(currentTable);
					status.setText(
							"Table schema fetched. Please use the \"Add constraint\" button to add constraints.");
				}
			}
		});
	}

	class cancelButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DefaultTableModel tm = (DefaultTableModel) table.getModel();
			tm.setNumRows(0);
			setVisible(false);
		}

	}

	class deleteButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int target = table.getSelectedRow();
			model.removeRow(target);
			constr.remove(target);
			if (model.getRowCount() != 0)
				model.setValueAt(null, model.getRowCount() - 1, 2);
		}

	}

	class addButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sdaf.setVisible(true);
		}

	}

	public void updateTables() {
		if (this.sql.connect()) {
			ArrayList<String> tables = sql.getProvider().getTables();
			tables.add(0, "Please select a table...");
			if (tables.size() != 0)
				this.t = tables;
		}
	}

	public void setSDAF(String tableName) {
		ArrayList<MetaRow> rows = this.sql.getProvider().getTableInfo(tableName);
		ArrayList<Pair<String, Boolean>> attrs = new ArrayList<Pair<String, Boolean>>();
		List<String> numbers = Arrays.asList("INTEGER", "INT", "SMALLINT", "TINYINT", "MEDIUMINT", "BIGINT", "FLOAT",
				"DOUBLE", "REAL");
		for (MetaRow r : rows) {
			if (numbers.contains(r.getType().toUpperCase()))
				attrs.add(new Pair<String, Boolean>(r.getName(), true));
			else
				attrs.add(new Pair<String, Boolean>(r.getName(), false));
		}
		this.sdaf = new SearchDataAddFrame(currentTable, this, attrs);
	}

	public void setButtonVisibility(boolean v) {
		add.setVisible(v);
		delete.setVisible(v);
	}

	public void addConstraint(Constraint c) {
		this.constr.add(c);
		this.updateConstrList();
	}

	private void updateConstrList() {
		ArrayList<String> connector = new ArrayList<>();
		for (int i = 0; i < this.constr.size() - 1; i++) {
			if (this.table.getValueAt(i, 2) == null)
				connector.add("AND");
			else
				connector.add((String) this.table.getValueAt(i, 2));
		}
		this.model.setRowCount(0);
		for (int i = 0; i < this.constr.size(); i++) {
			JComboBox<String> n = new JComboBox<String>();
			n.addItem("AND");
			n.addItem("OR");
			Object[] row;
			if (i != this.constr.size() - 1)
				row = new Object[] { this.constr.get(i).getAttr(), this.constr.get(i).getCond(), connector.get(i) };
			else
				row = new Object[] { this.constr.get(i).getAttr(), this.constr.get(i).getCond(), null };
			this.model.addRow(row);
			table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(n));
		}
	}
}
