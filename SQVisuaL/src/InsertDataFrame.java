import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

public class InsertDataFrame extends JFrame {
	public JTable table;
	public InsertDataFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		String[] columns = {"Target attribute", "Value"};
		
		table = new JTable(new DefaultTableModel(columns, 0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(2, 2));
		
		JButton edit = new JButton("Edit value");
		panel_1.add(edit);
		
		JLabel placeholder = new JLabel("");
		panel_1.add(placeholder);
		
		JButton cancel = new JButton("Cancel");
		panel_1.add(cancel);
		
		JButton execute = new JButton("Execute");
		panel_1.add(execute);
		
		Object[] row = { "Attr1", "Val1" };
		
		
		
	    model.addRow(row);
	}

}
