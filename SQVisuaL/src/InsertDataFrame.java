import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;

class InsertDataFrame extends JFrame {
	private boolean tableSelected;
	public JTable table;
	public JButton edit, cancel, execute;
	private JButton update;
	private JComboBox<String> tables;
	public InsertDataFrame(ArrayList<String> t) {
		super("SQVisuaL - Insert new data");
		this.tableSelected = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		String[] columns = {"Target attribute", "Value", "Mandatory"};
		
		table = new JTable(new DefaultTableModel(columns, 0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(2, 2));
		
		edit = new JButton("Edit value");
		edit.setVisible(false);
		panel_1.add(edit);
		
		JLabel placeholder = new JLabel("");
		panel_1.add(placeholder);
		
		cancel = new JButton("Cancel");
		panel_1.add(cancel);
		
		execute = new JButton("Execute");
		execute.setVisible(false);
		panel_1.add(execute);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tables = new JComboBox<String>();
		for(String name : t) tables.addItem(name);
		panel.add(tables);
		
		update = new JButton("Refresh tables");
		panel.add(update);
		
		Object[] row = { "Attr1", "Val1" };
		
	    model.addRow(row);
	}
	public void revealButtons() {
		this.edit.setVisible(true);
		this.execute.setVisible(true);
	}
}
