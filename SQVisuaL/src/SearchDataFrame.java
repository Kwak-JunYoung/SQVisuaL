import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class SearchDataFrame extends JFrame {
	public JTable table;
	public JButton add, delete, cancel, execute;
	public DefaultTableModel model;
	private JScrollPane scrollPane;
	private JComboBox<String> tables;
	private JButton update;
	private JTextField textField;
	public SearchDataAddFrame sdaf;
	public SearchDataFrame() {
	   sdaf = new SearchDataAddFrame();
      
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setBounds(100, 100, 450, 300);
      
	   JPanel panel_1 = new JPanel();
	   getContentPane().add(panel_1, BorderLayout.SOUTH);
	   panel_1.setLayout(new GridLayout(2, 2));
      
	   add = new JButton("Add Constraint");
	   panel_1.add(add);
      
	   delete = new JButton("Delete Constraint");
	   panel_1.add(delete);
      
	   cancel = new JButton("Cancel");
	   panel_1.add(cancel);
      
	   execute = new JButton("Execute Query");
	   panel_1.add(execute);
      
      
      
	   String[] columns = {"Column Name", "Constraint"};
	   table = new JTable(new DefaultTableModel(columns, 0));
	   model = (DefaultTableModel) table.getModel();
      
	   scrollPane = new JScrollPane();
	   getContentPane().add(scrollPane, BorderLayout.WEST);
	   scrollPane.setViewportView(table);
	   
	   JPanel panel_2 = new JPanel();
	   getContentPane().add(panel_2, BorderLayout.NORTH);
      
	   tables = new JComboBox<String>();
	   panel_2.add(tables);
      
	   update = new JButton("Refresh tables");
	   panel_2.add(update);
	   sdaf.apply.addActionListener(new ActionListener() {
		   public void actionPerformed (ActionEvent e) {
			   Object[] row = {sdaf.column.getText(), sdaf.constraint.getText()};
			   model.addRow(row);
			   //setVisible(true);
		   }
	   });
	   sdaf.cancel.addActionListener(new ActionListener() {
		   public void actionPerformed (ActionEvent e) {
			   sdaf.setVisible(false);
		   }
	   });
	   add.addActionListener(new ActionListener() {
		   public void actionPerformed (ActionEvent e) {
			   System.out.println(this);
			   sdaf.setVisible(true);
		   }
	   });
	}
}