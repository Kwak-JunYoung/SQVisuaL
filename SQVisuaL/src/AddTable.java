import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddTable extends JFrame {
	public JTable table;
	public JButton Add, Delete, Cancel, Create;
	private AddColumnFrame acf;
	private DeleteColumnFrame dcf;
	private JPanel panel_2;
	private JLabel status;
	private JLabel tableNameLabel;
	private JTextField tableName;

	public AddTable(MainFrame mf, SQVisuaL sql) {
		acf = new AddColumnFrame(this);
		dcf = new DeleteColumnFrame(this);
		String column[] = { "Column Name", "Data Type", "Primary Key?", "Can be NULL?", "Must be unique?" };

		table = new JTable(new DefaultTableModel(column, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(2, 2));

		Add = new JButton("Add Column");
		Add.setVisible(true);
		panel_1.add(Add);

		Delete = new JButton("Delete Column");
		Delete.setVisible(true);
		panel_1.add(Delete);

		Cancel = new JButton("Cancel");
		Cancel.setVisible(true);
		panel_1.add(Cancel);

		Create = new JButton("Create");
		Create.setVisible(true);
		panel_1.add(Create);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tableNameLabel = new JLabel("Table name:");
		panel.add(tableNameLabel);

		tableName = new JTextField();
		panel.add(tableName);
		tableName.setColumns(10);

		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(scrollPane);

		status = new JLabel("Error message will be displayed here.");
		panel_2.add(status, BorderLayout.SOUTH);

		setSize(500, 400);
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acf.setVisible(true);
			}
		});
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dcf.setVisible(true);
			}
		});
		Create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the table name.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				ArrayList<Integer> pkidx = new ArrayList<>(), unique = new ArrayList<>();
				String q = "CREATE TABLE `" + tableName.getText() + "` (";
				String pks = "PRIMARY KEY (";
				String uni = "UNIQUE (";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int rc = model.getRowCount();
				if (rc == 0) {
					JOptionPane.showMessageDialog(null, "There are no columns.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				for (int i = 0; i < rc; i++) {
					q += "`" + (String) model.getValueAt(i, 0) + "` " + (String) model.getValueAt(i, 1);
					if (((String) model.getValueAt(i, 2)).equals("Yes"))
						pkidx.add(i);
					if (((String) model.getValueAt(i, 4)).equals("Yes"))
						unique.add(i);
					if (((String) model.getValueAt(i, 3)).equals("No"))
						q += " NOT NULL";
					if (i != rc - 1)
						q += ", ";
				}
				for (int i = 0; i < pkidx.size(); i++) {
					pks += "`" + (String) model.getValueAt(i, 0) + "`";
					if (i != pkidx.size() - 1)
						pks += ", ";
				}
				pks += ")";
				for (int i = 0; i < unique.size(); i++) {
					uni += "`" + (String) model.getValueAt(i, 0) + "`";
					if (i != unique.size() - 1)
						uni += ", ";
				}
				uni += ")";
				if (pkidx.size() > 0)
					q += ", " + pks;
				if (unique.size() > 0)
					q += ", " + uni;
				q += ");";
				SQLException err = sql.getProvider().updateQueryReturnErr(q);
				if (err != null)
					setErrorMsg(err.getMessage());
				else {
					mf.updateTable(tableName.getText());
					mf.setTable();
					setVisible(false);
				}
			}
		});
	}

	public void setErrorMsg(String e) {
		this.status.setText(e);
	}

	public void addRow(Object[] tuple) {
		((DefaultTableModel) this.table.getModel()).addRow(tuple);
	}
	
	public void deleteRow(String name) {
		for(int i = 0; i < this.table.getRowCount(); i++) {
			if(this.table.getValueAt(i, 0) != null && this.table.getValueAt(i, 0).equals(name)) {
				((DefaultTableModel) this.table.getModel()).removeRow(i);
				return;
			}
		}
	}
}