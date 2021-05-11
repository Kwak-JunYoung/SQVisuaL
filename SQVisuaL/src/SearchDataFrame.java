import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SearchDataFrame extends JFrame {
	public JTable table;
	public SearchDataFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		panel.add(table);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(3, 2));
		
		JButton add = new JButton("Add new constraint");
		panel_1.add(add);
		
		JButton edit = new JButton("Edit constraint");
		panel_1.add(edit);
		
		JButton delete = new JButton("Delete constraint");
		panel_1.add(delete);
		
		JLabel placeholder = new JLabel("");
		panel_1.add(placeholder);
		
		JButton cancel = new JButton("Cancel");
		panel_1.add(cancel);
		
		JButton execute = new JButton("Execute");
		panel_1.add(execute);
		
		TableColumn attr = new TableColumn();
		attr.setHeaderValue("Target attribute");
		
		table.addColumn(attr);
	}

}
